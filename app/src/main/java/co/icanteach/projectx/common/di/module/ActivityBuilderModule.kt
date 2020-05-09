package co.icanteach.projectx.common.di.module

import co.icanteach.projectx.MainActivity
import co.icanteach.projectx.common.di.scope.ActivityScope
import co.icanteach.projectx.ui.populartvshows.PopularTvShowActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBuilderModule {

    @ActivityScope
    @get:ContributesAndroidInjector(modules = [PopularTvShowActivityModule::class])
    val mainActivity: MainActivity
}