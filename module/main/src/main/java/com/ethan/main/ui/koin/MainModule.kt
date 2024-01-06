package com.ethan.main.ui.koin

import com.ethan.main.ui.repository.RepositoryCategory
import com.ethan.main.ui.repository.RepositoryHome
import com.ethan.main.ui.repository.RepositoryMine
import com.ethan.main.ui.repository.RepositorySystem
import com.ethan.main.ui.viewmodel.ViewModelCategory
import com.ethan.main.ui.viewmodel.ViewModelHome
import com.ethan.main.ui.viewmodel.ViewModelMine
import com.ethan.main.ui.viewmodel.ViewModelSystem
import org.koin.dsl.module


private val viewModelModule = module {
    single { ViewModelHome(application = get(), mRepositoryHome = get()) }
    single { ViewModelMine(application = get(), mRepositoryMine = get()) }
    single { ViewModelSystem(app = get()) }
    single { ViewModelCategory(application = get()) }
}
private val repositoryModule = module {
    single { RepositoryHome() }
    single { RepositoryCategory() }
    single { RepositoryMine() }
    single { RepositorySystem() }
}


var mainModule = viewModelModule + repositoryModule