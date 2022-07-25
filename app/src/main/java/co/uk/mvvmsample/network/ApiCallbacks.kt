package mvvm.f4wzy.com.network

import com.google.gson.JsonArray
import com.google.gson.JsonObject

/**
 * Created by prahalad on 29-11-2019.
 */

interface ApiCallbacks {
    /**
     * method will call when api response is success. response status is success
     *
     * @param jsonObject response json object body
     * @param apiName    api name
     */
    fun onSuccess(jsonObject: JsonObject, apiName: String)

    /**
     * method will call when api response is success. response status is success
     *
     * @param jsonArray response json object body
     * @param apiName    api name
     */
    fun onSuccess(jsonArray: JsonArray, apiName: String)

    /**
     * method will call when api response is error. response status is not success
     *
     * @param jsonObject response json object body
     * @param apiName    api name
     */
    fun onError(jsonObject: JsonObject, apiName: String)


    /**
     * method will call when api response is error. response status is not success
     *
     * @param jsonArray response json object body
     * @param apiName    api name
     */
    fun onError(jsonArray: JsonArray, apiName: String)


}
