package co.icanteach.projectx.common.di.component

import android.content.Context
import co.icanteach.projectx.InterviewApplication
import co.icanteach.projectx.common.di.module.ActivityBuilderModule
import co.icanteach.projectx.common.di.module.DataModule
import co.icanteach.projectx.common.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuilderModule::class,
        ViewModelModule::class,
        DataModule::class
    ]
)
interface AppComponent : AndroidInjector<InterviewApplication> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AndroidInjector<InterviewApplication>
    }
}