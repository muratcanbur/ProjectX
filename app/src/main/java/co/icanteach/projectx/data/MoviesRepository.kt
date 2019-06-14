package co.icanteach.projectx.data

import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MoviesRepository @Inject constructor(val moviesRemoteDataSource: MoviesRemoteDataSource) {

    fun fetchMovies(page: Int) =
        moviesRemoteDataSource
            .fetchMovies(page)
            .subscribeOn(Schedulers.io())
}