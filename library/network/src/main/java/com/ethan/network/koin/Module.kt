package com.ethan.network.koin

import com.ethan.network.ssl.TrustAllCerts
import org.koin.dsl.module



val networkModule= module {
    single { TrustAllCerts() }
}