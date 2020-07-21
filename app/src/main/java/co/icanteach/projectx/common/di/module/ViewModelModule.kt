package co.icanteach.projectx.common.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.icanteach.projectx.common.di.ViewModelFactory
import co.icanteach.projectx.common.di.key.ViewModelKey
import co.icanteach.projectx.ui.populartvshows.PopularTVShowsViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(ApplicationComponent::class)
interface ViewModelModule {

    @get:IntoMap
    @get:Binds
    @get:ViewModelKey(PopularTVShowsViewModel::class)
    val PopularTVShowsViewModel.popularTVShowsViewModel: ViewModel

    @get:Binds
    val ViewModelFactory.viewModelFactory: ViewModelProvider.Factory
}