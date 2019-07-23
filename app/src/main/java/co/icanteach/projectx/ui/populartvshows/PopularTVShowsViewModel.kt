package co.icanteach.projectx.ui.populartvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import co.icanteach.projectx.common.Resource
import co.icanteach.projectx.common.Status
import co.icanteach.projectx.domain.FetchPopularTvShowUseCase
import co.icanteach.projectx.ui.populartvshows.model.PopularTvShowItem
import co.icanteach.projectx.util.BaseViewModel
import co.icanteach.projectx.util.CoroutinesDispatcherProvider
import kotlinx.coroutines.launch
import javax.inject.Inject

class PopularTVShowsViewModel @Inject constructor(
    coroutinesDispatcherProvider: CoroutinesDispatcherProvider,
    private val fetchPopularTvShowUseCase: FetchPopularTvShowUseCase
) : BaseViewModel(coroutinesDispatcherProvider) {

    private val popularTvShowsLiveData = MutableLiveData<PopularTVShowsFeedViewState>()

    val getPopularTvShowsLiveData: LiveData<PopularTVShowsFeedViewState>
        get() = popularTvShowsLiveData

    fun fetchMovies(page: Int) {
        onMoviesResultReady(Resource(Status.LOADING, null, null))
        launch {
            onMoviesResultReady(
                fetchPopularTvShowUseCase
                    .fetchMovies(page)
            )
        }
    }

    private fun onMoviesResultReady(resource: Resource<List<PopularTvShowItem>>) {
        popularTvShowsLiveData.value = PopularTVShowsFeedViewState(
            status = resource.status,
            error = resource.error,
            data = resource.data
        )
    }
}