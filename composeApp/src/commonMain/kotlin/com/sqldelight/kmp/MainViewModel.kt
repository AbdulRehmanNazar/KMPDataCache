package com.sqldelight.kmp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sqldelight.kmp.data.PostSDK
import com.sqldelight.kmp.domain.Post
import com.sqldelight.kmp.domain.RequestState
import kotlinx.coroutines.launch

typealias CachedPosts = MutableState<RequestState<List<Post>>>

class MainViewModel(private val postSDK: PostSDK) : ViewModel() {
    var allPosts: CachedPosts = mutableStateOf(RequestState.Idle)
        private set

    init {
        viewModelScope.launch {
            allPosts.value = postSDK.getAllPosts()
        }
    }

}