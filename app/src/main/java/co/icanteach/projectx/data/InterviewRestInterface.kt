package co.icanteach.projectx.data

import co.icanteach.projectx.data.feed.response.PopularTVShowsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface InterviewRestInterface {

    @GET("tv/popular")
    fun fetchMovies(@Query("api_key") apiKey: String, @Query("page") page: Int): Observable<PopularTVShowsResponse>
}