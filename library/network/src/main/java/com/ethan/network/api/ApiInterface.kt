package com.ethan.network.api

import com.ethan.common.bean.BeanBanner
import com.ethan.network.response.ResponseBase
import retrofit2.http.GET
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
    suspend fun getHomeBanner():ResponseBase<MutableList<BeanBanner>>?


    @GET("/article/list/{page}/json")
    suspend fun getHomeArticleList(@Path("page") page:Int,@Query("page_size") pageSize:Int):ResponseBase<MutableList<BeanBanner>>?

}