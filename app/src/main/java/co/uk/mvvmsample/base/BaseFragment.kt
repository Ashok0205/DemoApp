package co.uk.mvvmsample.base

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import java.io.File

open class BaseFragment : Fragment(){

    var commonCallbacks : CommonCallbacks? = null


    override fun onAttach(context: Context) {
        commonCallbacks = context as CommonCallbacks
        super.onAttach(context)
    }

    override fun onDetach() {
        commonCallbacks = null
        super.onDetach()
    }

    fun showProgress(){
        commonCallbacks?.showProgress()
    }

    fun hideProgress(){
        commonCallbacks?.hideProgress()
    }


    open fun onFragBack() : Boolean{
        return findNavController().navigateUp()
    }

    open fun onFilePicked(pickedFileUri : String){}

    open fun onApiRequestFailed(apiUrl: String, errCode: Int, errorMessage: String) : Boolean {return false}

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }



}