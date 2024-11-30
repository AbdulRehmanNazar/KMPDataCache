package com.sqldelight.kmp.di

import com.russhwolf.settings.Settings
import com.sqldelight.kmp.MainViewModel
import com.sqldelight.kmp.data.PostSDK
import com.sqldelight.kmp.data.local.AndroidDataBaseDriverFactory
import com.sqldelight.kmp.data.local.DataBaseDriverFactory
import com.sqldelight.kmp.data.local.LocalDataBase
import com.sqldelight.kmp.data.remote.PostAPI
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module

actual val targerModule: Module = module {
    single<DataBaseDriverFactory> {
        AndroidDataBaseDriverFactory(androidContext())
    }
}