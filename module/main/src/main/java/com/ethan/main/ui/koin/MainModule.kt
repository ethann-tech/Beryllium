package com.ethan.main.ui.koin

import com.ethan.main.ui.repository.RepositoryHome
import com.ethan.main.ui.viewmodel.ViewModelHome
import org.koin.dsl.module


private val viewModelModule = module {
    single { ViewModelHome(application = get(),mRepositoryHome = get()) }
}
private val repositoryModule = module {
    single { RepositoryHome() }
}


var mainModule = viewModelModule + repositoryModule