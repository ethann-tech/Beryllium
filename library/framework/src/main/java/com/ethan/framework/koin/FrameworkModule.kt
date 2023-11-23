package com.ethan.framework.koin

import com.google.gson.GsonBuilder
import org.koin.dsl.module

val frameworkModule = module {
    single { GsonBuilder().create() }
}