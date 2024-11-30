package com.sqldelight.kmp

import androidx.compose.ui.window.ComposeUIViewController
import com.sqldelight.kmp.di.initializeKoin

fun MainViewController() = ComposeUIViewController(
    configure = { initializeKoin() }
) { App() }