package co.icanteach.projectx.ui.populartvshows

import co.icanteach.projectx.common.Resource
import co.icanteach.projectx.common.ui.EMPTY
import co.icanteach.projectx.ui.populartvshows.model.PopularTvShowItem

class PopularTVShowsFeedViewState(
    val resource: Resource<List<PopularTvShowItem>>
) {
    val popularTvShows = if (resource is Resource.Success) resource.data else emptyList()
    val loading = resource is Resource.Loading
    val errorMessage = if (resource is Error) resource.message else String.EMPTY
    val showErrorMessage = resource is Resource.Error
}