package co.icanteach.projectx.ui.populartvshows

import co.icanteach.projectx.common.Status
import co.icanteach.projectx.data.feed.response.PopularTVShowsResponse
import co.icanteach.projectx.ui.populartvshows.model.PopularTvShowItem

class PopularTVShowsFeedViewState(
    val status: Status,
    val error: Throwable? = null,
    val data: List<PopularTvShowItem>? = null
) {
    fun getPopularTvShows() = data ?: mutableListOf()

    fun isLoading() = status == Status.LOADING

    fun getErrorMessage() = error?.message

    fun shouldShowErrorMessage() = error != null
}