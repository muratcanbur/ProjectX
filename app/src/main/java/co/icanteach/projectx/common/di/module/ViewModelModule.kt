package co.icanteach.projectx.common.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.icanteach.projectx.common.di.ViewModelFactory
import co.icanteach.projectx.common.di.key.ViewModelKey
import co.icanteach.projectx.ui.PopularTVShowsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @IntoMap
    @Binds
    @ViewModelKey(PopularTVShowsViewModel::class)
    abstract fun provideMoviesViewModel(moviesViewModel: PopularTVShowsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}