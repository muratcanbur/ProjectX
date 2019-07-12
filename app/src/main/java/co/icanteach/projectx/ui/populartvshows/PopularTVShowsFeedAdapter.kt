package co.icanteach.projectx.ui.populartvshows

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.icanteach.projectx.R
import co.icanteach.projectx.common.ui.inflate
import co.icanteach.projectx.data.feed.response.PopularTVShowItemResponse
import co.icanteach.projectx.databinding.ItemPopularTvShowsFeedBinding
import co.icanteach.projectx.ui.populartvshows.model.PopularTvShowItem
import javax.inject.Inject

class PopularTVShowsFeedAdapter @Inject constructor() :
    RecyclerView.Adapter<PopularTVShowsFeedAdapter.PopularTVShowsFeedItemViewHolder>() {

    private var popularTvShows: MutableList<PopularTvShowItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularTVShowsFeedItemViewHolder {
        val itemBinding = parent.inflate<ItemPopularTvShowsFeedBinding>(R.layout.item_popular_tv_shows_feed, false)
        return PopularTVShowsFeedItemViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = popularTvShows.size

    override fun onBindViewHolder(holder: PopularTVShowsFeedItemViewHolder, position: Int) {
        holder.bind(getTvShow(position))
    }

    private fun getTvShow(position: Int) = popularTvShows[position]

    fun setTvShows(tvShows: List<PopularTvShowItem>) {
        val beforeSize = popularTvShows.size
        popularTvShows.addAll(tvShows)
        notifyItemRangeInserted(beforeSize, tvShows.size)
    }

    inner class PopularTVShowsFeedItemViewHolder(private val binding: ItemPopularTvShowsFeedBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(tvShow: PopularTvShowItem) {
            with(binding) {
                viewState = PopularTVShowsFeedItemViewState(tvShow)
                executePendingBindings()
            }
        }

    }
}