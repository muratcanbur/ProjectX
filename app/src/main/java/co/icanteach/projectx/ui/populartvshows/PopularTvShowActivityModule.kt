package co.icanteach.projectx.ui.populartvshows

import co.icanteach.projectx.common.di.scope.ActivityScope
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class PopularTvShowActivityModule {

    @ActivityScope
    @get:Provides
    val popularTVShowsFeedAdapter = PopularTVShowsFeedAdapter()
}