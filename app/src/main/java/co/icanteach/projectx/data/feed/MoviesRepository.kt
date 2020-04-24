package co.icanteach.projectx.data.feed

import co.icanteach.projectx.common.Resource
import co.icanteach.projectx.common.ui.remote
import co.icanteach.projectx.data.feed.response.PopularTVShowsResponse
import io.reactivex.Observable
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val moviesRemoteDataSource: MoviesRemoteDataSource) {

    fun fetchMovies(page: Int): Observable<Resource<PopularTVShowsResponse>> =
        moviesRemoteDataSource
            .fetchMovies(page)
            .remote()

}