package co.icanteach.projectx

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import co.icanteach.projectx.common.EndlessScrollListener
import co.icanteach.projectx.common.observeNonNull
import co.icanteach.projectx.common.runIfNull
import co.icanteach.projectx.databinding.ActivityMainBinding
import co.icanteach.projectx.ui.populartvshows.PopularTVShowsFeedAdapter
import co.icanteach.projectx.ui.populartvshows.PopularTVShowsStatusViewState
import co.icanteach.projectx.ui.populartvshows.PopularTVShowsViewModel
import co.icanteach.projectx.ui.populartvshows.model.PopularTvShowItem
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    internal lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    @Inject
    internal lateinit var tvShowsFeedAdapter: PopularTVShowsFeedAdapter

    private lateinit var moviesViewModel: PopularTVShowsViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        moviesViewModel =
            ViewModelProvider(this, viewModelProviderFactory)
                .get(PopularTVShowsViewModel::class.java)

        moviesViewModel.contents_.observeNonNull(this) { contents ->
            renderPopularTVShows(contents)
        }

        moviesViewModel.status_.observeNonNull(this) { contents ->
            renderStatusResult(contents)
        }

        savedInstanceState.runIfNull {
            fetchMovies(FIRST_PAGE)
        }
        initPopularTVShowsRecyclerView()
    }

    private fun renderStatusResult(statusViewState: PopularTVShowsStatusViewState) {
        binding.viewState = statusViewState
        binding.executePendingBindings()
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

    private fun renderPopularTVShows(contents: List<PopularTvShowItem>) {
        tvShowsFeedAdapter.setTvShows(contents)
    }

    private fun fetchMovies(page: Int) {
        moviesViewModel.fetchMovies(page)
    }

    companion object {
        const val FIRST_PAGE = 1
    }
}