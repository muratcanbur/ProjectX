package co.icanteach.projectx.domain

import co.icanteach.projectx.common.Resource
import co.icanteach.projectx.data.feed.MoviesRepository
import co.icanteach.projectx.ui.populartvshows.model.PopularTvShowItem
import io.reactivex.Observable
import javax.inject.Inject

class FetchPopularTvShowUseCase @Inject constructor(
    private val repository: MoviesRepository,
    private val mapper: PopularTvShowMapper
) {

    fun fetchMovies(page: Int): Observable<Resource<List<PopularTvShowItem>>> {
        return repository
            .fetchMovies(page)
            .map { resource ->
                resource.map { mapper.mapFrom(it) }
            }
    }
}