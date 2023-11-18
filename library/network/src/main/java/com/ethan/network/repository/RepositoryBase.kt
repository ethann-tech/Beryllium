package com.ethan.network.repository

import com.ethan.network.exception.ApiException
import com.ethan.network.response.ResponseBase
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull

open class RepositoryBase {
    private val mLogger: org.slf4j.Logger = org.slf4j.LoggerFactory.getLogger(this::class.java)
    private val mGson:Gson = Gson()
    suspend fun <T> requestResponse(requestCall: suspend () -> ResponseBase<T>?): T? {
        val response = withContext(Dispatchers.IO) {
            withTimeoutOrNull(10 * 1000) {
                requestCall()
            }
        } ?: return null
        mLogger.debug("LOG:RepositoryBase:requestResponse response={}",mGson.toJson(response) )
        if (response.isFailed()) {
            throw ApiException(response.code, response.message)
        }
        return response.data

    }
}