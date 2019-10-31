package co.icanteach.projectx.data.feed

import android.util.Log
import co.icanteach.projectx.common.Resource
import co.icanteach.projectx.common.ui.applyLoading
import co.icanteach.projectx.data.feed.response.PopularTVShowsResponse
import co.icanteach.projectx.data.local.entity.PopularTVShowItemEntity
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class MoviesRepository @Inject constructor(
    private val moviesRemoteDataSource: MoviesRemoteDataSource,
    private val moviesLocalDataSource: MoviesLocalDataSource
) {

    fun fetchMoviesFromRemote(page: Int): Observable<Resource<PopularTVShowsResponse>> =
        moviesRemoteDataSource
            .fetchMovies(page)
            .map { Resource.success(it) }
            .onErrorReturn { Resource.error(it) }
            .subscribeOn(Schedulers.io())
            .compose(applyLoading())


    fun fetchMoviesFromLocal(): Observable<Resource<List<PopularTVShowItemEntity>>> =
        moviesLocalDataSource
            .fetchMovies()
            .toObservable()
            .map { Resource.success(it) }
            .onErrorReturn { Resource.error(it) }
            .subscribeOn(Schedulers.io())
            .compose(applyLoading())

    fun storeMoviesToLocal(list: List<PopularTVShowItemEntity>): Disposable? {
        return Observable.fromCallable {
            moviesLocalDataSource
                .storeMovies(list)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe {
                Log.d("", "Inserted ${list.size} articleService from API in DB...")
            }.also {
                CompositeDisposable(it)
            }
    }


}