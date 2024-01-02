package com.ethan.common.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class BeanArticleTag(
    @SerializedName("name")
    var name: String = "",
    @SerializedName("url")
    var url: String = ""
):Serializable