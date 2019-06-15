package co.icanteach.projectx.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.icanteach.projectx.R
import co.icanteach.projectx.common.ui.inflate
import co.icanteach.projectx.data.feed.TvShow
import co.icanteach.projectx.databinding.ItemPopularTvShowsFeedBinding
import javax.inject.Inject

class PopularTVShowsFeedAdapter @Inject constructor() :
    RecyclerView.Adapter<PopularTVShowsFeedAdapter.PopularTVShowsFeedItemViewHolder>() {

    private var popularTvShows: MutableList<TvShow> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularTVShowsFeedItemViewHolder {
        val itemBinding = parent.inflate<ItemPopularTvShowsFeedBinding>(R.layout.item_popular_tv_shows_feed, false)
        return PopularTVShowsFeedItemViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = popularTvShows.size

    override fun onBindViewHolder(holder: PopularTVShowsFeedItemViewHolder, position: Int) {
        holder.bind(getTvShow(position))
    }

    private fun getTvShow(position: Int) = popularTvShows[position]

    fun setTvShows(tvShows: List<TvShow>) {
        val beforeSize = popularTvShows.size
        popularTvShows.addAll(tvShows)
        notifyItemRangeInserted(beforeSize, tvShows.size)
    }

    inner class PopularTVShowsFeedItemViewHolder(val binding: ItemPopularTvShowsFeedBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(tvShow: TvShow) {
            with(binding) {
                viewState = PopularTVShowsFeedItemViewState(tvShow)
                executePendingBindings()
            }
        }

    }
}