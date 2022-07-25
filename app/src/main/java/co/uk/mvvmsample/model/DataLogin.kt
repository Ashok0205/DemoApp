package co.uk.mvvmsample.model

import com.google.gson.annotations.SerializedName

data class DataLogin (val username: String, val password:String, val errMsgStatus:Boolean,val errMsg:String)
