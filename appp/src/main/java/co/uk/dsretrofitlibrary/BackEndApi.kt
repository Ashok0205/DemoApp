package mvvm.f4wzy.com.network

import com.google.gson.JsonObject

import retrofit2.Call
import retrofit2.http.*

interface BackEndApi {

    @POST
    fun LOGIN(@Url apiName: String): Call<Any>

    @GET
    fun apiFormHeaderExtra(@Url url: String): Call<Any>




}

