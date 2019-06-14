package co.icanteach.projectx.data

import com.google.gson.annotations.SerializedName

class TvShow(
    @SerializedName("poster_path")
    val imageUrl: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("vote_average")
    val rating: String?
)