package co.icanteach.projectx.ui

import android.content.Context
import co.icanteach.projectx.R
import co.icanteach.projectx.data.feed.TvShow

class PopularTVShowsFeedItemViewState(private val tvShow: TvShow) {

    fun getImageUrl() = tvShow.imageUrl

    fun getTvShowName() = tvShow.name

    fun getTvShowRating(context: Context): String {
        val placeHolder = context.getString(R.string.movie_rating)
        return String.format(placeHolder, tvShow.rating)
    }
}