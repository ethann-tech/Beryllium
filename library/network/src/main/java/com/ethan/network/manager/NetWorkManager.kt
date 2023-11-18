package com.ethan.network.manager

import com.ethan.framework.ApplicationLifecycle
import com.ethan.network.HttpConstants
import com.ethan.network.api.ApiInterface
import com.ethan.network.interceptor.HttpLoggerInterceptor
import com.ethan.network.ssl.TrustAllCerts
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.security.SecureRandom
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import kotlin.math.max

class NetWorkManager private constructor() {
    private lateinit var retrofit: Retrofit
    private val  trustAllCerts: TrustAllCerts = TrustAllCerts()
    private val  sslSocketFactory =provideSSLSocketFactory()
    private val mLogger: org.slf4j.Logger = org.slf4j.LoggerFactory.getLogger(this.javaClass)

    init {
        retrofit = provideRetrofit()
    }

    companion object {
        val instance: NetWorkManager by lazy { Holder.holder }
    }

    private object Holder {
        val holder = NetWorkManager()
    }
    fun <T> provideService(cls: Class<T>): T = retrofit.create(cls)



    private fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(HttpConstants.BASE_URL)
        .client(provideOkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()


    private fun provideOkHttpClient( ): OkHttpClient = OkHttpClient()
        .newBuilder()
        .connectTimeout(timeout = 30L, TimeUnit.SECONDS)
        .readTimeout(timeout = 30L, TimeUnit.SECONDS)
        .cache(Cache(directory = ApplicationLifecycle.instance().cacheDir, maxSize = 100*1024*102))
        .retryOnConnectionFailure(retryOnConnectionFailure = true)
        .addInterceptor(HttpLoggingInterceptor(logger = HttpLoggerInterceptor())
        .setLevel(HttpLoggingInterceptor.Level.BASIC))
        .sslSocketFactory(sslSocketFactory, trustAllCerts)
        .hostnameVerifier(hostnameVerifier = { _, _ -> true }).build()


    fun provideSSLSocketFactory(): SSLSocketFactory {
        var sslSocketFactory: SSLSocketFactory? = null
        try {
            val sc = SSLContext.getInstance("TLS")
            sc.init(null, arrayOf(trustAllCerts), SecureRandom())
            sslSocketFactory = sc.socketFactory

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return sslSocketFactory!!
    }

}

