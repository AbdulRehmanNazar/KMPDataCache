package com.sqldelight.kmp.data.local

import app.cash.sqldelight.db.SqlDriver
import com.sql_kmp.PostDatabase
import com.sqldelight.kmp.domain.Post

interface DataBaseDriverFactory {
    fun createDriver(): SqlDriver
}

class LocalDataBase(dataBaseDriverFactory: DataBaseDriverFactory) {
    private val dataBase = PostDatabase(dataBaseDriverFactory.createDriver())
    private val query = dataBase.postDatabaseQueries

    fun readAllPost(): List<Post> {
        println("INFO: Reading all posts from database")
        return query.readAllPosts().executeAsList()
            .map {
                Post(
                    userId = it.userId.toInt(),
                    thumbnail = it.thumbnail,
                    id = it.id.toInt(),
                    title = it.title,
                    body = it.body
                )
            }
    }

    fun insertPost(posts: List<Post>) {
        println("INFO: Inserting posts into database")
        query.transaction {
            posts.forEach {
                query.insertPost(
                    userId = it.userId.toLong(),
                    thumbnail = it.thumbnail,
                    id = it.id.toLong(),
                    title = it.title,
                    body = it.body
                )
            }
        }
    }

    fun removeAllPost() {
        query.removeAllPosts()
    }
}