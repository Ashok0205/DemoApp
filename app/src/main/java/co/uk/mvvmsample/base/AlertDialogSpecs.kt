package co.uk.mvvmsample.base

import android.content.DialogInterface
import co.uk.mvvmsample.R

class AlertDialogSpecs {
    var btnPos : String = "OK"
    var btnNeg : String = ""
    var message : String = ""
    var title : String = R.string.app_name.toString()
    var alertDialogBtnListener : DialogInterface.OnClickListener? = null
}