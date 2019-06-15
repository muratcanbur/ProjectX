package co.icanteach.projectx.ui

import co.icanteach.projectx.data.feed.TvShow

class PopularTVShowsFeedItemViewState(private val tvShow: TvShow) {

    fun getImageUrl() = tvShow.imageUrl

    fun getTvShowName() = tvShow.name

    fun getTvShowRating() = tvShow.rating
}