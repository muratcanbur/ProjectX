package co.icanteach.projectx.data.feed

import co.icanteach.projectx.common.Resource
import co.icanteach.projectx.common.Status
import co.icanteach.projectx.util.CoroutinesDispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject


class MoviesRepository @Inject constructor(
    private val coroutinesDispatcherProvider: CoroutinesDispatcherProvider,
    private val moviesRemoteDataSource: MoviesRemoteDataSource
) {
    suspend fun fetchMovies(page: Int) = withContext(coroutinesDispatcherProvider.default) {
        co.icanteach.projectx.common.runCatching {
            Resource(
                status = Status.SUCCESS,
                data = moviesRemoteDataSource.fetchMovies(page),
                error = null
            )
        }
    }
}