package co.uk.mvvmsample.splash

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import co.uk.mvvmsample.R
import co.uk.mvvmsample.base.BaseAtivity
import co.uk.mvvmsample.dashboard.DashBoardActivity
import co.uk.mvvmsample.login.LoginActivity
import co.uk.mvvmsample.utills.Constant

class SplashActivity : AppCompatActivity() {

    val mViewModel: SplashViewModel? = null
    lateinit var pref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window?.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        pref = applicationContext.getSharedPreferences(
            resources.getString(R.string.app_name),
            MODE_PRIVATE
        )

        setContentView(R.layout.activity_splash)

            Handler().postDelayed({
                if (pref.getBoolean(Constant.IS_LOGIN, false)) {
                    intent = Intent(this,DashBoardActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    intent = Intent(this,LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }

            }, 3000)
    }
}
