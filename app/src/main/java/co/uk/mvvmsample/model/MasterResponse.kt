package co.uk.mvvmsample.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MasterResponse<T>(

	@field:SerializedName("Data")
	var data: T? = null,

	@field:SerializedName("ResponseCode")
	var responseCode: Int = 0,

	@field:SerializedName("SuccessMsg")
	var successMsg: String = "",

	@field:SerializedName("FailureMsg")
	var failureMsg: String = "",

	@field:SerializedName("ValidationErrors")
	var validationErrors: List<String> = emptyList(),

	@field:SerializedName("ApiName")
	var apiName: String? = null
)