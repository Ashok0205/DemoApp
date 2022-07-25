package co.uk.mvvmsample.authenticate.socialsignup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import co.uk.mvvmsample.R
import co.uk.mvvmsample.base.ActivityNavigation
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_social_login.*

import com.google.android.gms.common.api.ApiException

import co.uk.mvvmsample.base.BaseAtivity
import co.uk.mvvmsample.databinding.ActivitySocialLoginBinding
import com.facebook.*
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager

import com.facebook.login.LoginResult


import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseUser
import java.util.*


class SocialLoginActivity : BaseAtivity(), View.OnClickListener, ActivityNavigation,
    FacebookCallback<LoginResult> {

    lateinit var socialLoginBinding: ActivitySocialLoginBinding
    lateinit var socialLoginViewModel: SocialLoginViewModel
    val GOOGLE_LOG_IN_RC = 1
    val FACEBOOK_LOG_IN_RC = 2
    var mAuth: FirebaseAuth? = null
    lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var callbackManager: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        socialLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_social_login)
        socialLoginViewModel = ViewModelProviders.of(this).get(SocialLoginViewModel::class.java)
        socialLoginBinding.socialviewmodel = socialLoginViewModel

        google_sign_in_button.setOnClickListener(this)

        // [START config_signin]
        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        // [END config_signin]

        googleSignInClient = GoogleSignIn.getClient(this, gso)
        mAuth = FirebaseAuth.getInstance()



        socialLoginViewModel.loginType?.observe(this, Observer {
            if(it.equals("Facebook")){
                callbackManager = CallbackManager.Factory.create()
                facebookLogin()
            }
        })


        // [END initialize_fblogin]
    }

    private fun facebookLogin() {
        /*fb_button.setPermissions(Arrays.asList("email", "public_profile"))*/

        LoginManager.getInstance().registerCallback(callbackManager,this)


      /*  fb_button.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                Log.e("facebook:onSuccess", "facebook:onSuccess:$loginResult")
                handleFacebookAccessToken(loginResult.accessToken)
            }

            override fun onCancel() {
                Log.e("onCancel", "facebook:onCancel")
                // [START_EXCLUDE]
                updateUI(null)
                // [END_EXCLUDE]
            }

            override fun onError(error: FacebookException) {
                Log.e("onError", "facebook:onError", error)
                // [START_EXCLUDE]
                updateUI(null)
                // [END_EXCLUDE]
            }
        })*/
    }

    override fun onSuccess(result: LoginResult?) {
        handleFacebookAccessToken(result!!.accessToken)
    }

    override fun onCancel() {
        Log.e("onCancel", "facebook:onCancel")
        updateUI(null)
    }

    override fun onError(error: FacebookException?) {
        Log.e("onError", "facebook:onError", error)
        updateUI(null)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.google_sign_in_button -> {
                Log.e("login", "Trying Google LogIn.")
                googleLogin()
            }


        }
    }

    // [START on_start_check_user]
    public override fun onStart() {
        super.onStart()
        val alreadyloggedAccount = GoogleSignIn.getLastSignedInAccount(this)
        if (alreadyloggedAccount != null) {
            Toast.makeText(this, "Already Logged In", Toast.LENGTH_SHORT).show()
            onLoggedIn(alreadyloggedAccount)
        } else {
            Log.e("notloggedin", "Not logged in")
        }
    }
    // [END on_start_check_user]

    private fun googleLogin() {
        Log.e("start login", "Starting Google LogIn Flow.")
        val signInIntent = googleSignInClient.getSignInIntent()
        startActivityForResult(signInIntent, GOOGLE_LOG_IN_RC)
    }

    private fun signOut() {
        googleSignInClient.signOut()
            .addOnCompleteListener(this, OnCompleteListener<Void> {
                // ...
            })

    }

    private fun revokeAccess() {
        googleSignInClient.revokeAccess()
            .addOnCompleteListener(this, OnCompleteListener<Void> {
                // ...
            })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GOOGLE_LOG_IN_RC){
            try {
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                val account = task.getResult(ApiException::class.java)
                onLoggedIn(account)
            } catch (e: ApiException) {
                // The ApiException status code indicates the detailed failure reason.
                Log.w("failed code", "signInResult:failed code=" + e.statusCode)
            }
        }

    }

     fun onLoggedIn(account: GoogleSignInAccount?) {


    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        Log.e("token", "handleFacebookAccessToken:$token")
        // [START_EXCLUDE silent]
        showProgress()

        // [END_EXCLUDE]

        val credential = FacebookAuthProvider.getCredential(token.token)
        mAuth?.signInWithCredential(credential)?.addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d("task_success", "signInWithCredential:success")
                val user = mAuth!!.currentUser
                updateUI(user)
            } else {
                // If sign in fails, display a message to the user.
                Log.w("failure", "signInWithCredential:failure", task.exception)
                Toast.makeText(baseContext, "Authentication failed.",
                    Toast.LENGTH_SHORT).show()
                updateUI(null)
            }

            // [START_EXCLUDE]
            hideProgress()
            // [END_EXCLUDE]
        }
    }
    private fun updateUI(user: FirebaseUser?) {
        hideProgress()
        if (user != null) {
            Toast.makeText(this,user.displayName,Toast.LENGTH_SHORT).show()
        }
    }



    override fun onBackPressed() {
        super.onBackPressed()
        signOut()
        revokeAccess()
    }


}
