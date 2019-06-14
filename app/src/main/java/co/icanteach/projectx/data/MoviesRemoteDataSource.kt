package co.icanteach.projectx.data

import javax.inject.Inject

class MoviesRemoteDataSource @Inject constructor(private val restInterface: InterviewRestInterface) {

    fun fetchMovies(page: Int) = restInterface.fetchMovies("", page)
}