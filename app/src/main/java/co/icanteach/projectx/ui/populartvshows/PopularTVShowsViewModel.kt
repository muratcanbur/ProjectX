package co.icanteach.projectx.ui.populartvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import co.icanteach.projectx.common.*
import co.icanteach.projectx.ui.populartvshows.model.PopularTvShowItem
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class PopularTVShowsViewModel @Inject constructor(
    private val fetchPopularTvShowUseCase: FetchPopularTvShowUseCase
) :
    ReactiveViewModel() {


    private val contents = MutableLiveData<List<PopularTvShowItem>>()
    val contents_: LiveData<List<PopularTvShowItem>> = contents

    private val status = MutableLiveData<PopularTVShowsStatusViewState>()
    val status_: LiveData<PopularTVShowsStatusViewState> = status

    fun fetchMovies(page: Int) {
        fetchPopularTvShowUseCase
            .fetchMovies(page)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { list ->
                onMoviesContentResultReady(list)
            }
            .subscribe({ resource ->
                onMoviesStatusResultReady(resource)
            }, {})
            .also { disposable += it }
    }

    private fun onMoviesStatusResultReady(resource: Resource<List<PopularTvShowItem>>) {

        val viewState = when (resource) {
            is Resource.Success -> PopularTVShowsStatusViewState(Status.Content)
            is Resource.Error -> PopularTVShowsStatusViewState(Status.Error(resource.exception))
            Resource.Loading -> PopularTVShowsStatusViewState(Status.Loading)
        }
        status.value = viewState
    }

    private fun onMoviesContentResultReady(results: List<PopularTvShowItem>) {
        contents.value = results
    }
}