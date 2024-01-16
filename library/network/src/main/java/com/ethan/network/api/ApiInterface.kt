package com.ethan.network.api

import com.ethan.common.bean.BeanBanner
import com.ethan.common.bean.BeanCategoryResponse
import com.ethan.common.bean.BeanLogin
import com.ethan.common.bean.BeanProjectCategory
import com.ethan.common.bean.BeanResponseArticle
import com.ethan.common.bean.BeanSystem
import com.ethan.network.response.ResponseBase
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * API接口
 */
interface ApiInterface {
    /**
     * 首页轮播图
     */
    @GET("/banner/json")
    suspend fun getHomeBanner(): ResponseBase<MutableList<BeanBanner>>?

    /**
     * Mine 我的推荐
     */
    @GET("/article/list/{page}/json")
    suspend fun getHomeArticleList(@Path("page") page: Int, @Query("page_size") pageSize: Int): ResponseBase<BeanResponseArticle>

    /**
     * 登录接口
     */
    @POST("/user/login")
    suspend fun requestLogin(@Query("username") username: String, @Query("password") password: String): ResponseBase<BeanLogin>?

    /**
     * 项目分类
     */
    @GET("project/tree/json")
    suspend fun requestProjectCategory(): ResponseBase<List<BeanProjectCategory>>
    @GET("project/tree/json")
    suspend fun requestProjectTabs(): ResponseBase<List<BeanProjectCategory>>


    @GET("/navi/json")
    suspend fun requestCategoryList(): ResponseBase<List<BeanCategoryResponse>>

    /**
     * 体系一级分类
     */
    @GET("/tree/json")
    suspend fun requestSystemList(): ResponseBase<List<BeanSystem>>

    @GET("/article/list/{page}/json")
    suspend fun getArticleListByCid(@Path("page") page: Int, @Query("cid") cid: Int, @Query("page_size") pageSize: Int): ResponseBase<BeanResponseArticle>

}