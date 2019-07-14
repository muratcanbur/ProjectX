package co.icanteach.projectx

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import co.icanteach.projectx.common.ui.EndlessScrollListener
import co.icanteach.projectx.common.ui.observeNonNull
import co.icanteach.projectx.common.ui.runIfNull
import co.icanteach.projectx.databinding.ActivityMainBinding
import co.icanteach.projectx.ui.populartvshows.PopularTVShowsFeedAdapter
import co.icanteach.projectx.ui.populartvshows.PopularTVShowsFeedViewState
import co.icanteach.projectx.ui.populartvshows.PopularTVShowsViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    internal lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    @Inject
    internal lateinit var tvShowsFeedAdapter: PopularTVShowsFeedAdapter

    private lateinit var moviesViewModel: PopularTVShowsViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        moviesViewModel =
            ViewModelProviders.of(this, viewModelProviderFactory).get(PopularTVShowsViewModel::class.java)

        moviesViewModel.getPopularTvShowsLiveData().observeNonNull(this) {
            renderPopularTVShows(it)
        }

        savedInstanceState.runIfNull {
            fetchMovies(FIRST_PAGE)
        }
        initPopularTVShowsRecyclerView()
    }

    private fun initPopularTVShowsRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(this)
        binding.recyclerView.apply {
            adapter = tvShowsFeedAdapter
            layoutManager = linearLayoutManager
            addOnScrollListener(object : EndlessScrollListener(linearLayoutManager) {
                override fun onLoadMore(page: Int) {
                    fetchMovies(page)
                }
            })
        }
    }

    private fun renderPopularTVShows(feedViewState: PopularTVShowsFeedViewState) {
        with(binding) {
            viewState = feedViewState
            executePendingBindings()
        }
        tvShowsFeedAdapter.setTvShows(feedViewState.popularTvShows)
    }

    private fun fetchMovies(page: Int) {
        moviesViewModel.fetchMovies(page)
    }

    companion object {
        const val FIRST_PAGE = 1
    }
}