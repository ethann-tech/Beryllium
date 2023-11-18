package com.ethan.network.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class ResponseBase<T>(
    @SerializedName(value = "data")
    var data: T?,
    @SerializedName(value = "code", alternate = ["errorCode"])
    var code: Int = 0,
    @SerializedName(value = "message", alternate = ["errorMessage", "msg"])
    var message: String = ""
) : Serializable{
    /**
     * 判定接口返回是否正常
     */
    fun isFailed(): Boolean = code != 0

  
}