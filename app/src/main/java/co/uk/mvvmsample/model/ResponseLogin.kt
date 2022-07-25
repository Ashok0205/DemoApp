package co.uk.mvvmsample.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResponseLogin(

	@field:SerializedName("UserId")
	val userId: String? = null,

	@field:SerializedName("FirstName")
	val firstName: String? = null,

	@field:SerializedName("LastName")
	val lastName: String? = null,

	@field:SerializedName("Email")
	val email: String? = null,

	@field:SerializedName("AuthorizationToken")
	val authorizationToken: String? = null,

	@field:SerializedName("ProfilePicture")
	val profilePicture: String? = null,

	@field:SerializedName("Contact")
	val contact: String? = null,

	@field:SerializedName("DepartmentId")
	val departmentId: Int? = null,

	@field:SerializedName("BusinessId")
	val businessId: Int? = null
)