package co.icanteach.projectx.ui.populartvshows

import co.icanteach.projectx.ui.populartvshows.model.PopularTvShowItem

class PopularTVShowsFeedItemViewState(private val tvShow: PopularTvShowItem) {

    fun getImageUrl() = tvShow.imageUrl

    fun getTvShowName() = tvShow.name
    fun getTvShowOverview() = tvShow.overview
}