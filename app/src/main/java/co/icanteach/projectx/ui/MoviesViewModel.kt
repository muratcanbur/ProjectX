package co.icanteach.projectx.ui

import android.util.Log
import co.icanteach.projectx.common.RxAwareViewModel
import co.icanteach.projectx.data.MoviesRepository
import javax.inject.Inject

class MoviesViewModel @Inject constructor(private val moviesRepository: MoviesRepository) : RxAwareViewModel() {
    fun fetchMovies(page: Int) =
        moviesRepository
            .fetchMovies(page)
            .subscribe {
                Log.v("TESTTEST", it.results.size.toString())
            }
}