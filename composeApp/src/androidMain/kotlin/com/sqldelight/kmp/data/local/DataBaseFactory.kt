package com.sqldelight.kmp.data.local

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.sql_kmp.PostDatabase

class AndroidDataBaseDriverFactory(private val context: Context) : DataBaseDriverFactory {
    override fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            PostDatabase.Schema,
            context, "post.db"
        )
    }
}