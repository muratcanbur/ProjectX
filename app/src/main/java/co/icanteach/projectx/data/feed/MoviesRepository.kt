package co.icanteach.projectx.data.feed

import co.icanteach.projectx.common.Resource
import co.icanteach.projectx.common.ui.applyLoading
import co.icanteach.projectx.data.feed.response.PopularTVShowsResponse
import co.icanteach.projectx.data.local.entity.PopularTVShowItemEntity
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class MoviesRepository @Inject constructor(
    private val moviesRemoteDataSource: MoviesRemoteDataSource,
    private val moviesLocalDataSource: MoviesLocalDataSource
) {

    fun fetchMovies(page: Int): Observable<Resource<PopularTVShowsResponse>> =
        moviesRemoteDataSource
            .fetchMovies(page)
            .map { Resource.success(it) }
            .onErrorReturn { Resource.error(it) }
            .subscribeOn(Schedulers.io())
            .compose(applyLoading())


    fun getMovies(): Observable<Resource<List<PopularTVShowItemEntity>>> =
        moviesLocalDataSource
            .getMovies()
            .toObservable()
            .map { Resource.success(it) }
            .onErrorReturn { Resource.error(it) }
            .subscribeOn(Schedulers.io())
            .compose(applyLoading())

    fun storeMovies(list: List<PopularTVShowItemEntity>) {
        return moviesLocalDataSource.storeMovies(list)
    }


}