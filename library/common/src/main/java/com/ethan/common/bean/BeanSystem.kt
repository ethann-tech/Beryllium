package com.ethan.common.bean
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class BeanSystem(
    @SerializedName("articleList")
    var articleList: List<Any> = listOf(),
    @SerializedName("author")
    var author: String = "",
    @SerializedName("children")
    var children: List<BeanChildSystem> = listOf(),
    @SerializedName("courseId")
    var courseId: Int = 0,
    @SerializedName("cover")
    var cover: String = "",
    @SerializedName("desc")
    var desc: String = "",
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("lisense")
    var lisense: String = "",
    @SerializedName("lisenseLink")
    var lisenseLink: String = "",
    @SerializedName("name")
    var name: String = "",
    @SerializedName("order")
    var order: Int = 0,
    @SerializedName("parentChapterId")
    var parentChapterId: Int = 0,
    @SerializedName("type")
    var type: Int = 0,
    @SerializedName("userControlSetTop")
    var userControlSetTop: Boolean = false,
    @SerializedName("visible")
    var visible: Int = 0
):Serializable
