package  co.icanteach.projectx.common.di.module

import co.icanteach.projectx.data.NetworkModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module(
    includes = [NetworkModule::class]
)

@InstallIn(ApplicationComponent::class)
class DataModule