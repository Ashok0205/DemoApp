package co.uk.mvvmsample.login

import android.content.SharedPreferences
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import co.uk.mvvmsample.R
import co.uk.mvvmsample.base.BaseAtivity
import co.uk.mvvmsample.dashboard.DashBoardActivity
import co.uk.mvvmsample.databinding.ActivityLoginBinding
import co.uk.mvvmsample.utills.Util

import android.content.pm.PackageManager

import android.content.Context
import android.util.Base64
import android.util.Log
import co.uk.mvvmsample.authenticate.socialsignup.SocialLoginActivity
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class LoginActivity : BaseAtivity() {

    lateinit  var mBinding: ActivityLoginBinding
    lateinit var viewModel: LoginViewModel
    lateinit var pref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_login)


        pref = applicationContext.getSharedPreferences(
            resources.getString(R.string.app_name),
            MODE_PRIVATE
        )

        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java).apply {
            context = this@LoginActivity
        }
        mBinding.loginViewModel = viewModel


        mBinding.loginViewModel?.dataLogin?.observe(this, Observer { datalogin ->
             Util.hideSoftKeyBoard(this,mBinding.root)
            if(datalogin?.errMsgStatus!!){
                showMsgAlertDialog("Login",datalogin?.errMsg)
            }else{
               /* pref.edit().putBoolean(Constant.IS_LOGIN,true).commit()*/
               
                Util.showSnack(mBinding.root,"Welcome "+ datalogin.username!!)
                navigateTo(DashBoardActivity::class.java)
            }
            navigateTo(DashBoardActivity::class.java)
            printHashKey(this)
        })

    }
    fun printHashKey(pContext: Context) {
        try {
            val info = pContext.getPackageManager()
                .getPackageInfo(pContext.getPackageName(), PackageManager.GET_SIGNATURES)
            for (signature in info.signatures) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                val hashKey = String(Base64.encode(md.digest(), 0))
                Log.e("hash", "printHashKey() Hash Key: $hashKey")
            }
        } catch (e: NoSuchAlgorithmException) {
            Log.e("key", "printHashKey()", e)
        } catch (e: Exception) {
            Log.e("key", "printHashKey()", e)
        }

    }

}
