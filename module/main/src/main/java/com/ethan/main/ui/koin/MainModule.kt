package com.ethan.main.ui.koin

import com.ethan.main.ui.repository.RepositoryHome
import com.ethan.main.ui.repository.RepositoryMine
import com.ethan.main.ui.viewmodel.ViewModelHome
import com.ethan.main.ui.viewmodel.ViewModelMine
import org.koin.dsl.module


private val viewModelModule = module {
    single { ViewModelHome(application = get(), mRepositoryHome = get()) }
    single { ViewModelMine(application = get(), mRepositoryMine = get()) }
}
private val repositoryModule = module {
    single {
        RepositoryHome()
    }
    single {
        RepositoryMine()
    }
}


var mainModule = viewModelModule + repositoryModule