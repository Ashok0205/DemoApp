package co.uk.mvvmsample.base

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import co.uk.mvvmsample.R
import co.uk.mvvmsample.databinding.LayoutToolbarBinding
import co.uk.mvvmsample.fragments.home.HomeFragment
import co.uk.mvvmsample.utills.MyProgress
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.opengl.Visibility

import android.view.View
import android.view.View.VISIBLE
import co.uk.mvvmsample.model.MasterResponse


abstract class BaseAtivity :AppCompatActivity(),CommonCallbacks {

    var commonCallbacks : CommonCallbacks? = null
    var progressBar: ProgressBar? = null
    val keyboardController : MutableLiveData<Boolean> = MutableLiveData()
    internal var homeBackListner: HomeBackListner? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {

    }

    override fun setToolBar(toolbarbinding: LayoutToolbarBinding?,showBack: Boolean, title: String?) {
        if (toolbarbinding != null) {
            setSupportActionBar(toolbarbinding.toolbar)
            supportActionBar?.title = ""
            supportActionBar?.setDisplayShowTitleEnabled(true)
            if (title?.isNotEmpty() == true) {
                toolbarbinding.tvTitle.text = title
            } else {
                toolbarbinding.tvTitle.text = ""
            }

            if (showBack) {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                supportActionBar?.setDisplayShowHomeEnabled(true)

            } else {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
                supportActionBar?.setDisplayShowHomeEnabled(false)
            }
            toolbarbinding.screenBack.setOnClickListener(View.OnClickListener { homeBackListner?.clickHome() })


        }
    }


    interface HomeBackListner {
        fun clickHome()
    }

    fun setListener(homeBackListner: HomeBackListner) {
        this.homeBackListner = homeBackListner
    }


    override fun hideProgress() {
        MyProgress.hide(this)
    }

    override fun showProgress() {
        MyProgress.show(this)
    }



    fun <T> navigateTo(destination: Class<T>) {
        intent = Intent(this, destination)
        startActivity(intent)
        finish()
    }

    fun hideKeyboard() {
            runOnUiThread{
                keyboardController.value = false
            }

    }

    fun showMsgAlertDialog(
        title: String,
        msg: String
    ){
        var  builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(msg)
        builder.setPositiveButton("OK") { dialog, which ->
            dialog.cancel()
        }
        builder.show()
    }

    override fun onApiCallSuccess(apiUrl: String, body: MasterResponse<*>): Boolean {
        return false
    }

    override fun onApiCallFailed(apiUrl: String, errCode: Int, errorMessage: String): Boolean {
        runOnUiThread {

            //Give fragment a chance to handle api call failure
            if (getCurrentFragment(BaseFragment::class.java)?.onApiRequestFailed(
                    apiUrl,
                    errCode,
                    errorMessage
                ) == false
            ) {
                //fragment has nothing to do with this failure, can put some logic here
            }
        }
        return true
    }

    override fun isConnectedToNetwork(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}


