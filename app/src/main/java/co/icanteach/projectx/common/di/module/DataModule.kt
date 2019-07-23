package  co.icanteach.projectx.common.di.module

import co.icanteach.projectx.data.NetworkModule
import co.icanteach.projectx.util.CoroutinesDispatcherProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [NetworkModule::class]
)
class DataModule {

    @Provides
    @Singleton
    fun provideCoroutineDispacher(): CoroutinesDispatcherProvider = CoroutinesDispatcherProvider()

}