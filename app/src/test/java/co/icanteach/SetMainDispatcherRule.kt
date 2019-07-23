package co.icanteach

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@ExperimentalCoroutinesApi
class SetMainDispatcherRule : TestWatcher() {

    override fun starting(description: Description?) {
        Dispatchers.setMain(newSingleThreadContext("UI thread"))
    }

    override fun finished(description: Description?) {
        Dispatchers.resetMain()
    }

}