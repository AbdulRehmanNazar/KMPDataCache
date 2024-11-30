package com.sqldelight.kmp

import android.app.Application
import com.sqldelight.kmp.di.initializeKoin
import org.koin.android.ext.koin.androidContext

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initializeKoin(config = {
            androidContext(this@MyApplication)
        })
    }
}