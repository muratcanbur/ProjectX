package co.icanteach.projectx.common.di.module

import androidx.lifecycle.ViewModelProvider
import co.icanteach.projectx.common.di.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}