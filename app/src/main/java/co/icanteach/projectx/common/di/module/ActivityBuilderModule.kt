package co.icanteach.projectx.common.di.module

import co.icanteach.projectx.MainActivity
import co.icanteach.projectx.common.di.scope.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity
}