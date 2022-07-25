package co.uk.mvvmsample.network

import android.util.Log
import co.uk.mvvmsample.base.BaseAtivity
import com.google.gson.JsonObject
import mvvm.f4wzy.com.network.ApiCallbacks
import mvvm.f4wzy.com.network.BackEndApi
import mvvm.f4wzy.com.network.WebServiceClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object WebServiveCaller {

    fun callWebApiCallFormHeaderExtra( apiName: String,apiCallbacks: ApiCallbacks ) {

        val call = WebServiceClient.client.create(BackEndApi::class.java).apiCallFormHeaderExtra(apiName)

        call.enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {

                if (response.body() != null) {
                    apiCallbacks.onSuccess(response.body()!!, apiName)
                    Log.e("client_response", response.body().toString())
                } else {
                    apiCallbacks.onError(response.body()!!, apiName)
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {

                Log.e("Error", t.message)
            }
        }

        )

    }
}