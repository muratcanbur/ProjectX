package co.icanteach.projectx

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.icanteach.projectx.common.ui.observeNonNull
import co.icanteach.projectx.common.ui.runIfNull
import co.icanteach.projectx.databinding.ActivityMainBinding
import co.icanteach.projectx.ui.populartvshows.PopularTVShowsFeedAdapter
import co.icanteach.projectx.ui.populartvshows.PopularTVShowsFeedViewState
import co.icanteach.projectx.ui.populartvshows.PopularTVShowsViewModel
import co.icanteach.projectx.ui.populartvshows.model.PopularTvShowItem
import dagger.android.AndroidInjection
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    internal lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    @Inject
    internal lateinit var tvShowsFeedAdapter: PopularTVShowsFeedAdapter

    private lateinit var moviesViewModel: PopularTVShowsViewModel
    private lateinit var binding: ActivityMainBinding
    private var popularTvShowItemList: MutableList<PopularTvShowItem> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        moviesViewModel =
            ViewModelProviders.of(this, viewModelProviderFactory)
                .get(PopularTVShowsViewModel::class.java)

        moviesViewModel.getPopularTvShowsLiveData().observeNonNull(this) {
            renderPopularTVShows(it)
        }

        savedInstanceState.runIfNull {
            moviesViewModel.fetchMovies(FIRST_PAGE)
        }
        initPopularTVShowsRecyclerView()
    }

    private fun initPopularTVShowsRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(this)
        binding.recyclerView.apply {
            adapter = tvShowsFeedAdapter
            layoutManager = linearLayoutManager
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    val totalItemCount = linearLayoutManager.itemCount
                    val visibleItemCount = linearLayoutManager.childCount
                    val lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition()

                    moviesViewModel.listScrolled(visibleItemCount, lastVisibleItem, totalItemCount)
                }
            })
        }
    }

    private fun renderPopularTVShows(feedViewState: PopularTVShowsFeedViewState) {
        with(binding) {
            viewState = feedViewState
            executePendingBindings()
        }

        feedViewState.data?.let {
            popularTvShowItemList.addAll(it)
            tvShowsFeedAdapter.submitList(popularTvShowItemList)
        }
    }

    companion object {
        const val FIRST_PAGE = 1
    }
}