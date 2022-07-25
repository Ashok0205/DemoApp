package co.uk.mvvmsample.base

import android.content.DialogInterface
import co.uk.mvvmsample.model.MasterResponse

interface AsyncViewController {

    fun showProgress()

    fun hideProgress()

    fun onApiCallFailed(apiUrl: String, errCode: Int, errorMessage: String) : Boolean

    fun onApiCallSuccess(apiUrl: String, body : MasterResponse<*>) : Boolean

}