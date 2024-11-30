package com.sqldelight.kmp.di

import com.russhwolf.settings.Settings
import com.sqldelight.kmp.MainViewModel
import com.sqldelight.kmp.data.PostSDK
import com.sqldelight.kmp.data.local.DataBaseDriverFactory
import com.sqldelight.kmp.data.local.LocalDataBase
import com.sqldelight.kmp.data.remote.PostAPI
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

expect val targerModule: Module

val sharedModule = module {
    single<PostAPI> { PostAPI() }
    single<Settings> { Settings() }
    single<LocalDataBase> { LocalDataBase(get()) }
    single<PostSDK> {
        PostSDK(
            api = get(),
            dataBase = get(),
            settings = get()
        )
    }

    viewModel {
        MainViewModel(postSDK = get())
    }
}

fun initializeKoin(config: (KoinApplication. () -> Unit)? = null) {
    startKoin {
        config?.invoke(this)
        modules(targerModule, sharedModule)
    }
}