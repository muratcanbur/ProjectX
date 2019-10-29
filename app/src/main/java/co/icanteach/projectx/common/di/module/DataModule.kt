package  co.icanteach.projectx.common.di.module

import co.icanteach.projectx.data.CacheModule
import co.icanteach.projectx.data.NetworkModule
import dagger.Module

@Module(
    includes = [NetworkModule::class, CacheModule::class]
)
class DataModule