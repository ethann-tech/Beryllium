package com.ethan.common.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class BeanArticle (

    @SerializedName("adminAdd", alternate = ["admin_add"])
    @JvmField
    var adminAdd: Boolean = false,
    @SerializedName("apkLink")
    var apkLink: String = "",
    @SerializedName("audit")
    var audit: Int = 0,
    @SerializedName("author")
    var author: String = "",
    @SerializedName("canEdit")
    var canEdit: Boolean = false,
    @SerializedName("chapterId")
    var chapterId: Int = 0,
    @SerializedName("chapterName")
    var chapterName: String = "",
    @SerializedName("collect")
    var collect: Boolean = false,
    @SerializedName("courseId")
    var courseId: Int = 0,
    @SerializedName("desc")
    var desc: String = "",
    @SerializedName("descMd")
    var descMd: String = "",
    @SerializedName("envelopePic")
    var envelopePic: String = "",
    @SerializedName("fresh")
    var fresh: Boolean = false,
    @SerializedName("host")
    var host: String = "",
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("isAdminAdd")
    @JvmField
    var isAdminAdd: Boolean = false,
    @SerializedName("link")
    var link: String = "",
    @SerializedName("niceDate")
    var niceDate: String = "",
    @SerializedName("niceShareDate")
    var niceShareDate: String = "",
    @SerializedName("origin")
    var origin: String = "",
    @SerializedName("prefix")
    var prefix: String = "",
    @SerializedName("projectLink")
    var projectLink: String = "",
    @SerializedName("publishTime")
    var publishTime: Long = 0,
    @SerializedName("realSuperChapterId")
    var realSuperChapterId: Int = 0,
    @SerializedName("selfVisible")
    var selfVisible: Int = 0,
    @SerializedName("shareDate")
    var shareDate: Long = 0,
    @SerializedName("shareUser")
    var shareUser: String = "",
    @SerializedName("superChapterId")
    var superChapterId: Int = 0,
    @SerializedName("superChapterName")
    var superChapterName: String = "",
    @SerializedName("tags")
    var tags: List<BeanArticleTag> = listOf(),
    @SerializedName("title")
    var title: String = "",
    @SerializedName("type")
    var type: Int = 0,
    @SerializedName("userId")
    var userId: Int = 0,
    @SerializedName("visible")
    var visible: Int = 0,
    @SerializedName("zan")
    var zan: Int = 0):Serializable