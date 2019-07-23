package co.icanteach.projectx.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

data class CoroutinesDispatcherProvider(
    val main: CoroutineDispatcher = Main,
    val default: CoroutineDispatcher = Default,
    val io: CoroutineDispatcher = IO
)