package com.ethan.common.bean
import android.text.TextUtils
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class BeanUserInfo(
    @SerializedName("admin")
    var admin: Boolean = false,
    @SerializedName("chapterTops")
    var chapterTops: List<Any> = listOf(),
    @SerializedName("coinCount")
    var coinCount: Int = 0,
    @SerializedName("collectIds")
    var collectIds: List<Int> = listOf(),
    @SerializedName("email")
    var email: String = "",
    @SerializedName("icon")
    var icon: String = "",
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("nickname")
    var nickname: String = "",
    @SerializedName("password")
    var password: String = "",
    @SerializedName("publicName")
    var publicName: String = "",
    @SerializedName("token")
    var token: String = "",
    @SerializedName("type")
    var type: Int = 0,
    @SerializedName("username")
    var username: String = ""
):Serializable {
    fun isValid():Boolean{
        return TextUtils.isEmpty(username).not() || TextUtils.isEmpty(password).not()
    }
}