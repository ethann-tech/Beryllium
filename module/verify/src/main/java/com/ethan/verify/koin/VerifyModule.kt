package com.ethan.verify.koin

import com.ethan.verify.repository.RepositoryVerify
import com.ethan.verify.viewmodel.ViewModelVerify
import org.koin.dsl.module

private val viewModelModule = module {
    single { ViewModelVerify(application = get(), repositoryVerify = get()) }
}
private val repositoryModule = module {
    single { RepositoryVerify() }
}

val verifyModule = viewModelModule + repositoryModule