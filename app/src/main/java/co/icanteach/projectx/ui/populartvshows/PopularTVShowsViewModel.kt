package co.icanteach.projectx.ui.populartvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import co.icanteach.projectx.common.Resource
import co.icanteach.projectx.common.RxAwareViewModel
import co.icanteach.projectx.common.ui.plusAssign
import co.icanteach.projectx.domain.FetchPopularTvShowUseCase
import co.icanteach.projectx.ui.populartvshows.model.PopularTvShowItem
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class PopularTVShowsViewModel @Inject constructor(private val fetchPopularTvShowUseCase: FetchPopularTvShowUseCase) :
    RxAwareViewModel() {

    private var lastRequestedPage = 1

    private val popularTvShowsLiveData = MutableLiveData<PopularTVShowsFeedViewState>()

    fun getPopularTvShowsLiveData(): LiveData<PopularTVShowsFeedViewState> = popularTvShowsLiveData

    fun fetchMovies(page: Int) {
        fetchPopularTvShowUseCase
            .fetchMovies(page)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::onMoviesResultReady)
            .also {
                disposable += it
                lastRequestedPage += 1
            }
    }

    private fun onMoviesResultReady(resource: Resource<List<PopularTvShowItem>>) {
        popularTvShowsLiveData.value = PopularTVShowsFeedViewState(
            status = resource.status,
            error = resource.error,
            data = resource.data
        )
    }

    fun listScrolled(visibleItemCount: Int, lastVisibleItemPosition: Int, totalItemCount: Int) {
        if (visibleItemCount + lastVisibleItemPosition + VISIBLE_THRESHOLD >= totalItemCount) {
            val immutableQuery = lastQueryValue()
            if (immutableQuery != null) {
                fetchMovies(lastRequestedPage)
            }
        }
    }

    private fun lastQueryValue(): List<PopularTvShowItem>? = popularTvShowsLiveData.value?.data

    companion object {
        private const val VISIBLE_THRESHOLD = 4
    }
}