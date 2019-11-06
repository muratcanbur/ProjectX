package co.icanteach.projectx.domain

import android.util.Log
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
        return Observable.concatArrayEager(fetchMoviesFromRemote(page), fetchMoviesFromLocal())
    }

    private fun fetchMoviesFromRemote(page: Int): Observable<Resource<List<PopularTvShowItem>>> {
        return repository
            .fetchMoviesFromRemote(page)
            .map { resource ->
                Resource(
                    status = resource.status,
                    data = resource.data?.let { mapper.mapFromResponse(it) },
                    error = resource.error
                )
            }
//            .doOnNext {
//                repository.storeMoviesToLocal(mapper.mapFromModel(it?.data!!))
//            }


    }

    private fun fetchMoviesFromLocal(): Observable<Resource<List<PopularTvShowItem>>> {
        return repository
            .fetchMoviesFromLocal()
            .map { resource ->
                Resource(
                    status = resource.status,
                    data = resource.data?.let { mapper.mapFromEntity(it) },
                    error = resource.error
                )
            }
            .doOnError { Log.e("fetchMoviesFromLocal", "error : ", it) }

    }
}