package com.ethan.common.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class BeanVideoItem(
    @SerializedName("id")
    var id: String="",
    @SerializedName("title")
    var title: String="测试数据",
    @SerializedName("author")
    var author: String="Ethan",
    @SerializedName("cover")
    var cover: String,
    @SerializedName("play_count")
    var playCount: Int=5,
    @SerializedName("description")
    var description: String="描述",
):Serializable