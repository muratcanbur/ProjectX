package co.icanteach.projectx.data.feed.response

import com.google.gson.annotations.SerializedName

class PopularTVShowsResponse(
    @SerializedName("results")
    val results: List<PopularTVShowItemResponse>
)