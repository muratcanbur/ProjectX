package co.icanteach.projectx.data.feed

import co.icanteach.projectx.data.InterviewRestInterface
import javax.inject.Inject

class MoviesRemoteDataSource @Inject constructor(private val restInterface: InterviewRestInterface) {

    fun fetchMovies(page: Int) = restInterface
        .fetchMovies(API_KEY, page)

    companion object {
        const val API_KEY = "5d967c7c335764f39b1efbe9c5de9760"
    }
}