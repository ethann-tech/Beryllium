package com.ethan.common
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class BeanBanner(
    @SerializedName("desc")
    var desc: String = "",
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("imagePath")
    var imagePath: String = "",
    @SerializedName("isVisible")
    var isVisible: Int = 0,
    @SerializedName("order")
    var order: Int = 0,
    @SerializedName("title")
    var title: String = "",
    @SerializedName("type")
    var type: Int = 0,
    @SerializedName("url")
    var url: String = ""
):Serializable