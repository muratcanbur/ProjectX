package co.icanteach.projectx.data

import co.icanteach.projectx.data.feed.PopularTVShowsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface InterviewRestInterface {

    @GET("tv/popular")
    fun fetchMovies(@Query("page") page: Int): Observable<PopularTVShowsResponse>
}