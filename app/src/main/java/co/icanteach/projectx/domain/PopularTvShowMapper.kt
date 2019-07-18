package co.icanteach.projectx.domain

import co.icanteach.projectx.common.Mapper
import co.icanteach.projectx.data.feed.response.PopularTVShowsResponse
import co.icanteach.projectx.ui.populartvshows.model.PopularTvShowItem
import javax.inject.Inject

class PopularTvShowMapper @Inject constructor() : Mapper<PopularTVShowsResponse, List<PopularTvShowItem>> {

    override fun mapFrom(type: PopularTVShowsResponse): List<PopularTvShowItem> {
        return type.results.map { itemResponse ->
            PopularTvShowItem(
                imageUrl = itemResponse.imageUrl,
                name = itemResponse.name,
                overview = itemResponse.overview
            )
        }
    }
}