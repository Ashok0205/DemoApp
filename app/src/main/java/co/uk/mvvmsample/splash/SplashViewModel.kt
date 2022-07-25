package co.uk.mvvmsample.splash

import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.uk.mvvmsample.base.BaseAtivity
import co.uk.mvvmsample.login.LoginActivity

class SplashViewModel : ViewModel() {
    var proceedAhead = MutableLiveData<Boolean>()


}