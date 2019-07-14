package co.icanteach.projectx.data.feed

import co.icanteach.projectx.common.Resource
import co.icanteach.projectx.common.ui.handleNetworkError
import co.icanteach.projectx.data.feed.response.PopularTVShowsResponse
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class MoviesRepository @Inject constructor(private val moviesRemoteDataSource: MoviesRemoteDataSource) {

    fun fetchMovies(page: Int): Observable<Resource<PopularTVShowsResponse>> =
        moviesRemoteDataSource
            .fetchMovies(page)
            .map<Resource<PopularTVShowsResponse>> { Resource.Success(it) }
            .handleNetworkError()
            .subscribeOn(Schedulers.io())
            .startWith(Resource.Loading())
}