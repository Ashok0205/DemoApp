package co.uk.mvvmsample.utills

import android.util.Patterns
import androidx.databinding.ObservableField
import co.uk.mvvmsample.R
import co.uk.mvvmsample.base.MainApplication


class Validator {

    companion object {

        fun isEmailValid(email: String?, errMsgHolder: ObservableField<String>): Boolean {
            if (email?.isEmpty() == true) {
                errMsgHolder.set(MainApplication.get().getString(R.string.err_email_missing))
                return false
            } else errMsgHolder.set("")

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                errMsgHolder.set(MainApplication.get().getString(R.string.err_email_invalid))
                return false
            } else errMsgHolder.set("")

            return true
        }

        fun isTextValid(password: String?, errMsgHolder: ObservableField<String>): Boolean {
            if (password?.isEmpty() == true) {
                errMsgHolder.set(MainApplication.get().getString(R.string.err_name_missing))
                return false
            } else errMsgHolder.set("")

            return true
        }

        fun isPasswordValid(password: String?, errMsgHolder: ObservableField<String>): Boolean {
            if (password?.isEmpty() == true) {
                errMsgHolder.set(MainApplication.get().getString(R.string.err_password_missing))
                return false
            } else errMsgHolder.set("")

            if (password!!.length !in 8..20) {
                errMsgHolder.set(MainApplication.get().getString(R.string.err_password_length_invalid))
                return false
            } else errMsgHolder.set("")

            return true
        }


    }

}