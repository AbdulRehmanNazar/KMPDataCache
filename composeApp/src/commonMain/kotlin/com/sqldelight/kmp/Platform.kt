package com.sqldelight.kmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform