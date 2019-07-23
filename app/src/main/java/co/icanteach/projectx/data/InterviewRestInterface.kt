package co.icanteach.projectx.data

import co.icanteach.projectx.data.feed.response.PopularTVShowsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface InterviewRestInterface {

    @GET("tv/popular")
    suspend fun fetchMovies(@Query("page") page: Int): PopularTVShowsResponse
}