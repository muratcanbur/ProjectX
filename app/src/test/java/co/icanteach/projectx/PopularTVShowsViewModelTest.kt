package co.icanteach.projectx

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import co.icanteach.SetMainDispatcherRule
import co.icanteach.dispatcherProviderTest
import co.icanteach.projectx.common.Resource
import co.icanteach.projectx.common.Status
import co.icanteach.projectx.domain.FetchPopularTvShowUseCase
import co.icanteach.projectx.ui.populartvshows.PopularTVShowsFeedViewState
import co.icanteach.projectx.ui.populartvshows.PopularTVShowsViewModel
import co.icanteach.projectx.ui.populartvshows.model.PopularTvShowItem
import com.google.common.truth.Truth
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PopularTVShowsViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    var setMainDispatcherRule = SetMainDispatcherRule()

    @MockK
    lateinit var fetchPopularTvShowUseCase: FetchPopularTvShowUseCase

    private val dispatcherProvider = dispatcherProviderTest()

    private val popularTVShowsViewModel by lazy {
        PopularTVShowsViewModel(
            dispatcherProvider,
            fetchPopularTvShowUseCase
        )
    }

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `should show loading state first when loading popular tv shows`() {
        // Given
        val mockedObserver = createPopularTVShowsFeedObserver()
        popularTVShowsViewModel.getPopularTvShowsLiveData.observeForever(mockedObserver)

        coEvery { fetchPopularTvShowUseCase.fetchMovies(any()) } returns Resource.success(createPopularTVShows())

        // When
        popularTVShowsViewModel.fetchMovies(1)

        // Then
        val popularTVShowsFeedViewStateSlots = mutableListOf<PopularTVShowsFeedViewState>()
        verify(exactly = 2) { mockedObserver.onChanged(capture(popularTVShowsFeedViewStateSlots)) }

        val errorState = popularTVShowsFeedViewStateSlots[0]
        Truth.assertThat(errorState.status).isEqualTo(Status.LOADING)

        coVerify { fetchPopularTvShowUseCase.fetchMovies(any()) }
    }

    @Test
    fun `should show success state when loading popular tv shows successfully`() {
        // Given
        val mockedObserver = createPopularTVShowsFeedObserver()
        popularTVShowsViewModel.getPopularTvShowsLiveData.observeForever(mockedObserver)

        coEvery { fetchPopularTvShowUseCase.fetchMovies(any()) } returns Resource.success(createPopularTVShows())

        // When
        popularTVShowsViewModel.fetchMovies(1)

        // Then
        val popularTVShowsFeedViewStateSlots = mutableListOf<PopularTVShowsFeedViewState>()
        verify(exactly = 2) { mockedObserver.onChanged(capture(popularTVShowsFeedViewStateSlots)) }

        val errorState = popularTVShowsFeedViewStateSlots[1]
        Truth.assertThat(errorState.status).isEqualTo(Status.SUCCESS)

        coVerify { fetchPopularTvShowUseCase.fetchMovies(any()) }
    }

    @Test
    fun `should show error state when there is an error while loading popular tv shows`() {
        // Given
        val mockedObserver = createPopularTVShowsFeedObserver()
        popularTVShowsViewModel.getPopularTvShowsLiveData.observeForever(mockedObserver)

        coEvery { fetchPopularTvShowUseCase.fetchMovies(any()) } returns Resource.error(Exception("unhandled exception"))

        // When
        popularTVShowsViewModel.fetchMovies(1)

        // Then
        val popularTVShowsFeedViewStateSlots = mutableListOf<PopularTVShowsFeedViewState>()
        verify(exactly = 2) { mockedObserver.onChanged(capture(popularTVShowsFeedViewStateSlots)) }

        val errorState = popularTVShowsFeedViewStateSlots[1]
        Truth.assertThat(errorState.status).isEqualTo(Status.ERROR)

        coVerify { fetchPopularTvShowUseCase.fetchMovies(any()) }
    }

    private fun createPopularTVShowsFeedObserver(): Observer<PopularTVShowsFeedViewState> = spyk(Observer { })

    private fun createDummyTvShow(): PopularTvShowItem {
        return PopularTvShowItem(
            name = "Chernobyl",
            imageUrl = "/hlLXt2tOPT6RRnjiUmoxyG1LTFi.jpg",
            overview = "An unassuming mystery writer turned sleuth uses her professional insight to help solve real-life homicide cases."
        )
    }

    private fun createPopularTVShows(): List<PopularTvShowItem> {
        val popularTvShows = mutableListOf<PopularTvShowItem>()
        for (x in 0..10) {
            popularTvShows.add(createDummyTvShow())
        }
        return popularTvShows
    }
}