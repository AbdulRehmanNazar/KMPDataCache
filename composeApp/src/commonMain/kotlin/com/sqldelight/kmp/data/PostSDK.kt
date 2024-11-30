package com.sqldelight.kmp.data

import com.russhwolf.settings.Settings
import com.sqldelight.kmp.data.local.LocalDataBase
import com.sqldelight.kmp.data.remote.PostAPI
import com.sqldelight.kmp.domain.Post
import com.sqldelight.kmp.domain.RequestState
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlin.time.Duration.Companion.hours


const val FRESH_DATA_KEY = "freshDataTimeStamp"

class PostSDK(
    private val api: PostAPI,
    private val dataBase: LocalDataBase,
    private val settings: Settings
) {

    @Throws(Exception::class)
    suspend fun getAllPosts(): RequestState<List<Post>> {
        return try {
            val cachedData = dataBase.readAllPost()

            if (cachedData.isEmpty()) {
                settings.putLong(FRESH_DATA_KEY, Clock.System.now().toEpochMilliseconds())
                RequestState.Success(api.fetchAllPosts().also {
                    dataBase.removeAllPost()
                    dataBase.insertPost(it)
                })
            } else {
                if (isDataStale()) {
                    settings.putLong(FRESH_DATA_KEY, Clock.System.now().toEpochMilliseconds())
                    RequestState.Success(api.fetchAllPosts().also {
                        dataBase.removeAllPost()
                        dataBase.insertPost(it)
                    })
                } else {
                    RequestState.Success(cachedData)
                }
            }
        } catch (e: Exception) {
            RequestState.Error(e.message.toString())
        }
    }

    private fun isDataStale(): Boolean {
        val savedTimeStamp = Instant.fromEpochMilliseconds(
            settings.getLong(FRESH_DATA_KEY, defaultValue = 0L)
        )
        val currentTimeStamp = Clock.System.now()
        val difference = if (savedTimeStamp > currentTimeStamp) {
            savedTimeStamp.minus(currentTimeStamp)
        } else {
            currentTimeStamp.minus(savedTimeStamp)
        }
        return difference >= 24.hours
    }
}