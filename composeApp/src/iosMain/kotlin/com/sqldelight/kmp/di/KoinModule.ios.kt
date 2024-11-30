package com.sqldelight.kmp.di

import com.russhwolf.settings.Settings
import com.sqldelight.kmp.MainViewModel
import com.sqldelight.kmp.data.PostSDK
import com.sqldelight.kmp.data.local.DataBaseDriverFactory
import com.sqldelight.kmp.data.local.IOSDataBaseDriverFactory
import com.sqldelight.kmp.data.local.LocalDataBase
import com.sqldelight.kmp.data.remote.PostAPI
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

actual val targerModule: Module = module {
    single<DataBaseDriverFactory> {
        IOSDataBaseDriverFactory()
    }
}