package co.uk.mvvmsample.authenticate.socialsignup

import android.content.Intent
import android.provider.Settings.Secure.getString
import android.util.Log
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.uk.mvvmsample.R
import co.uk.mvvmsample.base.ActivityNavigation
import co.uk.mvvmsample.utills.LiveMessageEvent
import co.uk.mvvmsample.utills.Util
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.events.Event
import java.lang.Exception

class SocialLoginViewModel : ViewModel() {

    var loginType: MutableLiveData<String>? = null

    init {

        loginType = MutableLiveData("")
    }

    fun socialLogin(){
        loginType?.value = "Facebook"
    }

}