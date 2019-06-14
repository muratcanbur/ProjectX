package co.icanteach.projectx.ui

import android.util.Log
import co.icanteach.projectx.common.Resource
import co.icanteach.projectx.common.RxAwareViewModel
import co.icanteach.projectx.data.feed.MoviesRepository
import co.icanteach.projectx.data.feed.PopularTVShowsResponse
import javax.inject.Inject

class MoviesViewModel @Inject constructor(private val moviesRepository: MoviesRepository) : RxAwareViewModel() {
    fun fetchMovies(page: Int) =
        moviesRepository
            .fetchMovies(page)
            .subscribe { resource ->
                onMoviesResultReady(resource)
            }

    private fun onMoviesResultReady(resource: Resource<PopularTVShowsResponse>) {
        Log.v("TESTTEST", resource.status.name)
    }
}