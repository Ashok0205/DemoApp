package co.uk.mvvmsample.utills

import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Insets.add
import android.net.Uri
import android.text.TextUtils.replace
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import co.uk.mvvmsample.base.MainApplication
import com.google.android.material.snackbar.Snackbar
import java.io.File

class Util {

    companion object {
        fun log(msg: String) {
            Log.e("APP_LOGS", msg)
        }

        fun toast(s: String) {
            Toast.makeText(MainApplication.get().getContext(), s, Toast.LENGTH_SHORT).show()
        }

        fun getUriFromFilePath(path: String): Uri {
            return Uri.fromFile(File(path))
        }

        fun areAllPermissionsAccepted(grantResults: IntArray): Boolean {
            grantResults.forEach {
                if (it != PackageManager.PERMISSION_GRANTED) {
                    return false
                }
            }
            return true
        }

        fun getUriStringFromPath(path: String): String {
            return "file://$path"
        }

        fun isAPdf(path: String): Boolean {
            val extension = path.substring(path.lastIndexOf("."))
            return extension.equals(".pdf", ignoreCase = true)
        }

        fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int){
            supportFragmentManager.inTransaction { add(frameId, fragment) }
        }
        fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
            supportFragmentManager.inTransaction{replace(frameId, fragment)}
        }

        inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
            beginTransaction().func().commit()
        }

        /*fun openPdf(context: Context, filePath: String) {
            val file = File(filePath)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                val uri = com.theartofdev.edmodo.cropper.FileProvider.getUriFromFile(context, file)
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = uri
                intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                context.startActivity(intent)
            } else {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.setDataAndType(Uri.fromFile(file), "application/pdf")
                intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
                context.startActivity(intent)
            }
        }*/

        /* *//*Utility method for data binding*//*
        fun <T>getElementFromList(list : List<T>?, pos : Int) : T? {
            if (list == null) return null
            if (list.size < pos +1) return null
            return list[pos]
        }*/


        fun getElementFromList(list : List<String>?, pos : Int) : String? {
            if (list == null) return null
            if (list.size < pos +1) return null
            return list[pos]
        }

        fun showSnack(v : View, msg : String){
            Snackbar.make(v, msg, Snackbar.LENGTH_SHORT).show()
        }

        fun hideSoftKeyBoard(context: Context, view: View) {
            try {
                val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm?.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            } catch (e: Exception) {
                // TODO: handle exception
                e.printStackTrace()
            }

        }



    }
}