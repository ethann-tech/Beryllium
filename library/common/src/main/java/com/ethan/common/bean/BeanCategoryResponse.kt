package com.ethan.common.bean
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class BeanCategoryResponse(
    @SerializedName("articles")
    var articles: List<BeanCategorySub> = listOf(),
    @SerializedName("cid")
    var cid: Int = 0,
    @SerializedName("name")
    var name: String = ""
):Serializable
