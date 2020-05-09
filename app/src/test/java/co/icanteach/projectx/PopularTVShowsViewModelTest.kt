package co.icanteach.projectx

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import co.icanteach.RxImmediateSchedulerRule
import co.icanteach.projectx.common.Resource
import co.icanteach.projectx.ui.populartvshows.FetchPopularTvShowUseCase
import co.icanteach.projectx.ui.populartvshows.PopularTVShowsStatusViewState
import co.icanteach.projectx.ui.populartvshows.PopularTVShowsViewModel
import co.icanteach.projectx.ui.populartvshows.model.PopularTvShowItem
import com.google.common.truth.Truth.assertThat
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.reactivex.rxjava3.core.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PopularTVShowsViewModelTest {

    @MockK
    lateinit var fetchPopularTvShowUseCase: FetchPopularTvShowUseCase

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    private lateinit var popularTVShowsViewModel: PopularTVShowsViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        popularTVShowsViewModel = PopularTVShowsViewModel(fetchPopularTvShowUseCase)
    }

    @Test
    fun `given loading state, when fetchMovies called, then isLoading return true`() {

        // Given
        val mockedObserver = createPopularTVShowsFeedObserver()
        popularTVShowsViewModel.status_.observeForever(mockedObserver)

        every { fetchPopularTvShowUseCase.fetchMovies(any()) } returns
                Observable.just(Resource.Loading)

        // When
        popularTVShowsViewModel.fetchMovies(1)

        // Then
        val slot = slot<PopularTVShowsStatusViewState>()
        verify { mockedObserver.onChanged(capture(slot)) }

        assertThat(slot.captured.isLoading()).isTrue()

        verify { fetchPopularTvShowUseCase.fetchMovies(any()) }
    }

    @Test
    fun `given error state, when fetchMovies called, then update live data for error status`() {
        // Given
        val mockedObserver = createPopularTVShowsFeedObserver()
        popularTVShowsViewModel.status_.observeForever(mockedObserver)
        val message = "This is an error!"
        val resource = Resource.Error(Throwable(message))

        every { fetchPopularTvShowUseCase.fetchMovies(any()) } returns Observable.just(resource)

        // When
        popularTVShowsViewModel.fetchMovies(1)

        // Then
        val slot = slot<PopularTVShowsStatusViewState>()
        verify { mockedObserver.onChanged(capture(slot)) }

        assertThat(slot.captured.getErrorMessage()).isEqualTo(message)
        assertThat(slot.captured.isLoading()).isFalse()

        verify { fetchPopularTvShowUseCase.fetchMovies(any()) }
    }

    @Test
    fun `given succcess state, when fetchMovies called, then isLoading return false`() {
        // Given
        val mockedObserver = createPopularTVShowsFeedObserver()
        popularTVShowsViewModel.status_.observeForever(mockedObserver)
        val resource = Resource.Success(mutableListOf<PopularTvShowItem>())

        every { fetchPopularTvShowUseCase.fetchMovies(any()) } returns Observable.just(resource)


        // When
        popularTVShowsViewModel.fetchMovies(1)

        // Then
        val popularTVShowsFeedViewStateSlots = mutableListOf<PopularTVShowsStatusViewState>()
        verify { mockedObserver.onChanged(capture(popularTVShowsFeedViewStateSlots)) }

        // Then
        val slot = slot<PopularTVShowsStatusViewState>()
        verify { mockedObserver.onChanged(capture(slot)) }

        assertThat(slot.captured.isLoading()).isFalse()

        verify { fetchPopularTvShowUseCase.fetchMovies(any()) }
    }

    private fun createPopularTVShowsFeedObserver(): Observer<PopularTVShowsStatusViewState> =
        spyk(Observer { })
}