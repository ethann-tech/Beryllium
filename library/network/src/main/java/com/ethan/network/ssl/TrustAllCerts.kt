package com.ethan.network.ssl

import org.slf4j.LoggerFactory
import java.security.cert.X509Certificate
import javax.net.ssl.X509TrustManager
import javax.security.cert.CertificateException

class TrustAllCerts : X509TrustManager {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @Throws(CertificateException::class)
    override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {
    }

    @Throws(CertificateException::class)
    override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {
    }

    override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
}