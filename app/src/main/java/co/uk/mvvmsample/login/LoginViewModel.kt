package co.uk.mvvmsample.login

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.uk.mvvmsample.model.DataLogin
import co.uk.mvvmsample.network.WebServiveCaller
import co.uk.mvvmsample.network.WebUrlConstants
import co.uk.mvvmsample.utills.Util
import co.uk.mvvmsample.utills.Validator
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import mvvm.f4wzy.com.network.ApiCallbacks
import mvvm.f4wzy.com.network.BackEndApi
import mvvm.f4wzy.com.network.WebServiceClient
import okhttp3.MultipartBody
import okhttp3.RequestBody
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import co.uk.dslibrary.CustomProgressDialog
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.content.ContextCompat
import java.util.logging.Handler


class LoginViewModel()  :ViewModel(), ApiCallbacks {


    var name: ObservableField<String>? = null
    var password: ObservableField<String>? = null
    var errmsg: ObservableField<String>? = null
    var errmsgstatus: ObservableField<Boolean>? = null
    var dataLogin: MutableLiveData<DataLogin>? = null
    lateinit var context: Context

    private val progressDialog = CustomProgressDialog()

    init {
        name = ObservableField("")
        password = ObservableField("")
        errmsg = ObservableField("")
        errmsgstatus = ObservableField(false)
        dataLogin = MutableLiveData<DataLogin>()

    }

    fun onClickLogin(){

        progressDialog.show(context,"Please Wait...")
      /*  if(name?.get()!!.isNotEmpty()){
            var url = WebUrlConstants.LOGIN_URL + name?.get()!! + "&password=" + password?.get()!!
            WebServiveCaller.callWebApiCallFormHeaderExtra(url, this)
        }*/
        if(name?.get()!!.isNullOrBlank()){
            Util.toast("NAME")
            dataLogin?.value = DataLogin(name?.get()!!, password?.get()!!, errMsgStatus = true,errMsg = "Please enter name")
        } else if(password?.get()!!.isNullOrBlank()) {
            dataLogin?.value = DataLogin(name?.get()!!, password?.get()!!, errMsgStatus = true,errMsg = "Please enter password")
        }else{
            if(name?.get()!!.equals("Gourav")&& password?.get()!!.equals("login")){
                dataLogin?.value = DataLogin(name?.get()!!, password?.get()!!, errMsgStatus = false,errMsg = "")
            }else{
                dataLogin?.value = DataLogin(name?.get()!!, password?.get()!!, errMsgStatus = true,errMsg = "Wrong username password")
            }
        }

            /*WebServiceClient.client.create(BackEndApi::class.java).LOGIN(WebUrlConstants.LOGIN_URL).enqueue(object :Callback<Any>{
                override fun onFailure(call: Call<Any>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(call: Call<Any>, response: Response<Any>) {
                    android.os.Handler().postDelayed({
                        progressDialog.hide()
                    },2000)

                    var jsonStr : String = Gson().toJson(response.body())
                    var dataObj : DataLogin = Gson().fromJson(jsonStr,DataLogin::class.java)
                  //  dataLogin?.value = DataLogin(name?.get()!!, password?.get()!!, errMsgStatus = true,errMsg = msg)
                    Log.e("response", Gson().toJson(dataObj))

                }

            })*/
            /* WebServiceClient.client.create(BackEndApi::class.java).apiFormHeaderExtra(url = url)!!.enqueue(this)*/

    }

    override fun onSuccess(jsonObject: JsonObject, apiName: String) {

        if(jsonObject.has("error")){
            var msg:String = jsonObject.get("error").toString()
            Log.e("response", jsonObject.toString())
            Log.e("err_response", msg)
            dataLogin?.value = DataLogin(name?.get()!!, password?.get()!!, errMsgStatus = true,errMsg = msg)
        }
    }

    override fun onSuccess(jsonArray: JsonArray, apiName: String) {

    }

    override fun onError(jsonObject: JsonObject, apiName: String) {

    }

    override fun onError(jsonArray: JsonArray, apiName: String) {

    }

}




