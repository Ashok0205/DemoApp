package mvvm.f4wzy.com.network

import co.uk.mvvmsample.model.DataLogin
import com.google.android.gms.common.internal.safeparcel.SafeParcelable
import com.google.gson.JsonObject

import retrofit2.Call
import retrofit2.http.*

interface BackEndApi {

    @GET
    fun LOGIN(@Url apiName: String): Call<Any>

    @GET
    fun apiFormHeaderExtra(@Url url: String): Call<DataLogin>

    @GET
    fun apiCallFormHeaderExtra(@Url url: String): Call<JsonObject>


}

