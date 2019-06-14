package co.icanteach.projectx.data.feed

import co.icanteach.projectx.common.Resource
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * https://blog.danlew.net/2015/03/02/dont-break-the-chain/
 */
fun <T> applyLoading(): ObservableTransformer<Resource<T>, Resource<T>> = ObservableTransformer { upstream ->
    Observable.just(Resource.loading<T>()).concatWith(upstream)
}

class MoviesRepository @Inject constructor(private val moviesRemoteDataSource: MoviesRemoteDataSource) {

    fun fetchMovies(page: Int): Observable<Resource<PopularTVShowsResponse>> =
        moviesRemoteDataSource
            .fetchMovies(page)
            .map { Resource.success(it) }
            .onErrorReturn { Resource.error<PopularTVShowsResponse>(it) }
            .subscribeOn(Schedulers.io())
            .compose(applyLoading())
}