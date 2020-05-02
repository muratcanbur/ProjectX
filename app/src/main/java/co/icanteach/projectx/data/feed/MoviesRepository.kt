package co.icanteach.projectx.data.feed

import co.icanteach.projectx.common.Resource
import co.icanteach.projectx.data.feed.response.PopularTVShowsResponse
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val remoteDataSource: MoviesRemoteDataSource) {

    fun fetchMovies(page: Int): Observable<Resource<PopularTVShowsResponse>> {
        return remoteDataSource
            .fetchMovies(page)
            .map<Resource<PopularTVShowsResponse>> {
                Resource.Success(it)
            }.onErrorReturn { throwable ->
                Resource.Error(throwable)

            }.subscribeOn(Schedulers.io())

    }
}