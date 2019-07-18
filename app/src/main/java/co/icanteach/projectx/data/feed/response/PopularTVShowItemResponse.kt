package co.icanteach.projectx.data.feed.response

import com.google.gson.annotations.SerializedName

class PopularTVShowItemResponse(
    @SerializedName("poster_path")
    val imageUrl: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("overview")
    val overview: String?
)