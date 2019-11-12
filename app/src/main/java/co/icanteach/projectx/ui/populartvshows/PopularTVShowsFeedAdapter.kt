package co.icanteach.projectx.ui.populartvshows

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import co.icanteach.projectx.R
import co.icanteach.projectx.common.ui.inflate
import co.icanteach.projectx.databinding.ItemPopularTvShowsFeedBinding
import co.icanteach.projectx.ui.populartvshows.model.PopularTvShowItem
import javax.inject.Inject

class PopularTVShowsFeedAdapter @Inject constructor() :
    ListAdapter<PopularTvShowItem, RecyclerView.ViewHolder>(ITEM_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemBinding = parent.inflate<ItemPopularTvShowsFeedBinding>(R.layout.item_popular_tv_shows_feed, false)
        return PopularTVShowsFeedItemViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val showItem = getItem(position)
        if (showItem != null) {
            (holder as PopularTVShowsFeedItemViewHolder).bind(showItem)
        }
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

    companion object {
        private val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<PopularTvShowItem>() {
            override fun areItemsTheSame(oldItem: PopularTvShowItem, newItem: PopularTvShowItem): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: PopularTvShowItem, newItem: PopularTvShowItem): Boolean =
                oldItem == newItem
        }
    }
}