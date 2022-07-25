package co.uk.mvvmsample.model

import android.net.Uri
import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import com.google.gson.reflect.TypeToken



class WrapperMultiPartRequest{

    companion object{

        fun createMultiPartRequestWrapper(keyMapDataStr : String, listOfFilePaths : HashSet<String>) :  WrapperMultiPartRequest{
            val result = WrapperMultiPartRequest()
            result.keyValueMap = keyMapDataStr
            val files = ArrayList<File>()
            listOfFilePaths.forEach {
                val f = File(it)
                if (f.exists() && f.canRead()){
                    files.add(f)
                }
            }
            result.listOfFiles = files
            return result
        }
    }

    var keyValueMap : String = "{}"
    var listOfFiles : ArrayList<File> = ArrayList()


    fun getMultipartBody() : MultipartBody {

        val builder = MultipartBody.Builder()
        builder.setType(MultipartBody.FORM)

        builder.addFormDataPart("JsonModel", keyValueMap)
        for (i in listOfFiles) {
            builder.addFormDataPart(
                "Image",
                i.name,
                RequestBody.create(MediaType.parse("multipart/form-data"), i)
            )
        }
        return builder.build()
    }
}