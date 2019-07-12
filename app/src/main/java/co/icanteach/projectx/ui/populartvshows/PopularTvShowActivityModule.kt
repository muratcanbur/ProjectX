package co.icanteach.projectx.ui.populartvshows

import co.icanteach.projectx.common.di.scope.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class PopularTvShowActivityModule {

    @Provides
    @ActivityScope
    fun providePopularTVShowsFeedAdapter(): PopularTVShowsFeedAdapter {
        return PopularTVShowsFeedAdapter()
    }
}