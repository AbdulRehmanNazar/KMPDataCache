package com.sqldelight.kmp.data.remote

import com.sqldelight.kmp.domain.Post
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


const val ALL_POST_ENDPOINT = "https://jsonplaceholder.typicode.com/posts?_limit=10"

class PostAPI {
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                }
            )
        }
    }


    suspend fun fetchAllPosts(): List<Post> {
        println("INFO: Fetching all posts from network")
        return httpClient.get(urlString = ALL_POST_ENDPOINT)
            .body()
    }
}