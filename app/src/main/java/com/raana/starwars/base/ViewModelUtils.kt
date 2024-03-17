package com.raana.starwars.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState

@Composable
fun <T : Any> BaseViewModel<T>.state(): State<T> {
    return stateStateFlow.collectAsState()
}