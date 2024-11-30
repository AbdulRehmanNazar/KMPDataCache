package com.sqldelight.kmp.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.sql_kmp.PostDatabase

class IOSDataBaseDriverFactory() : DataBaseDriverFactory {
    override fun createDriver(): SqlDriver {
        return NativeSqliteDriver(
            PostDatabase.Schema,
            "post.db"
        )


    }
}