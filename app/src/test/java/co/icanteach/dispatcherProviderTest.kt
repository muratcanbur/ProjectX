package co.icanteach

import co.icanteach.projectx.util.CoroutinesDispatcherProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
fun dispatcherProviderTest() = CoroutinesDispatcherProvider(
    Dispatchers.Unconfined,
    Dispatchers.Unconfined,
    Dispatchers.Unconfined
)