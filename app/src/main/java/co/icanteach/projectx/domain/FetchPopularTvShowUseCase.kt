package co.icanteach.projectx.domain

import co.icanteach.projectx.common.Resource
import co.icanteach.projectx.common.Status
import co.icanteach.projectx.data.feed.MoviesRepository
import co.icanteach.projectx.util.CoroutinesDispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchPopularTvShowUseCase @Inject constructor(
    private val repository: MoviesRepository,
    private val mapper: PopularTvShowMapper,
    private val coroutinesDispatcherProvider: CoroutinesDispatcherProvider
) {

    suspend fun fetchMovies(page: Int) =
        withContext(coroutinesDispatcherProvider.default) {
            co.icanteach.projectx.common.runCatching {
                Resource(
                    status = Status.SUCCESS,
                    data = repository
                        .fetchMovies(page)
                        .data?.let { mapper.mapFrom(it) },
                    error = null
                )
            }
        }
}