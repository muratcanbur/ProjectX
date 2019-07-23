package co.icanteach.projectx.util

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel(
    private val coroutinesDispatcherProvider: CoroutinesDispatcherProvider
) : ViewModel(), CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + coroutinesDispatcherProvider.main

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}