package com.ethan.beryllium.koin

import com.ethan.common.koin.commonModule
import com.ethan.framework.koin.frameworkModule
import com.ethan.main.ui.koin.mainModule
import com.ethan.network.koin.networkModule
import com.ethan.verify.koin.verifyModule

val allModule = commonModule + frameworkModule + mainModule+ networkModule+ verifyModule