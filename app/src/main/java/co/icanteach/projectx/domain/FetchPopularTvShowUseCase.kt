package co.icanteach.projectx.domain

import android.util.Log
import co.icanteach.projectx.common.Resource
import co.icanteach.projectx.common.ui.doOnSuccess
import co.icanteach.projectx.data.feed.MoviesRepository
import co.icanteach.projectx.data.local.entity.PopularTVShowItemEntity
import co.icanteach.projectx.ui.populartvshows.model.PopularTvShowItem
import io.reactivex.Observable
import javax.inject.Inject

class FetchPopularTvShowUseCase @Inject constructor(
    private val repository: MoviesRepository,
    private val mapper: PopularTvShowMapper
) {

    fun fetchMovies(page: Int): Observable<Resource<List<PopularTvShowItem>>> {
        return Observable.concatArrayEager(getMoviesFromLocal(),fetchMoviesFromRemote(page))

    }

    private fun fetchMoviesFromRemote(page: Int): Observable<Resource<List<PopularTvShowItem>>> {
        return repository
            .fetchMovies(page)
            .map { resource ->
                Resource(
                    status = resource.status,
                    data = resource.data?.let { mapper.mapFromResponse(it) },
                    error = resource.error
                )
            }
            .doOnSuccess { resource ->
                Log.d("doOnSuccess","wollaa")
                if (page == 1 &&resource.data != null) {
                    storeMovies(mapper.mapFromItem(resource.data))
                }
            }
    }

    private fun getMoviesFromLocal(): Observable<Resource<List<PopularTvShowItem>>> {
        return repository
            .getMovies()
            .map { resource ->
                Resource(
                    status = resource.status,
                    data = resource.data?.let { mapper.mapFromEntity(it) },
                    error = resource.error
                )
            }

    }

    private fun storeMovies(list: List<PopularTVShowItemEntity>) {
        return repository
            .storeMovies(list)
    }
}

