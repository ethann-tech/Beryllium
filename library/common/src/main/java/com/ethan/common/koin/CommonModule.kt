package com.ethan.common.koin

import com.ethan.common.util.LogUtil
import org.koin.dsl.module

val commonModule = module {
    single { LogUtil() }

}