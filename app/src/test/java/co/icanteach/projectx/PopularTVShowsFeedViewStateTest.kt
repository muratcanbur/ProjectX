package co.icanteach.projectx

import co.icanteach.projectx.common.Resource
import co.icanteach.projectx.ui.populartvshows.PopularTVShowsFeedViewState
import com.google.common.truth.Truth
import org.junit.Test

class PopularTVShowsFeedViewStateTest {

    @Test
    fun `should return loading true when status is loading`() {

        // Given
        val givenViewState =
            PopularTVShowsFeedViewState(Resource.Loading())

        // When
        val actualResult = givenViewState.loading

        // Then
        Truth.assertThat(actualResult).isTrue()
    }

    @Test
    fun `should not return loading false when status is success`() {

        // Given
        val givenViewState =
            PopularTVShowsFeedViewState(Resource.Success(emptyList()))

        // When
        val actualResult = givenViewState.loading

        // Then
        Truth.assertThat(actualResult).isFalse()
    }

    @Test
    fun `should not return loading false when status is error`() {

        // Given
        val givenViewState = PopularTVShowsFeedViewState(Resource.Error(Exception("500 Internal Server Error")))

        // When
        val actualResult = givenViewState.loading

        // Then
        Truth.assertThat(actualResult).isFalse()
    }

    @Test
    fun `should return correct error message when status is error`() {

        // Given
        val givenViewState =
            PopularTVShowsFeedViewState(Resource.Error(Exception("500 Internal Server Error")))

        // When
        val actualResult = givenViewState.errorMessage

        // Then
        Truth.assertThat(actualResult).isEqualTo("500 Internal Server Error")
    }

    @Test
    fun `should return true for error placeholder visibility  when status is error`() {

        // Given
        val givenViewState =
            PopularTVShowsFeedViewState(Resource.Error(Exception("500 Internal Server Error")))

        // When
        val actualResult = givenViewState.showErrorMessage

        // Then
        Truth.assertThat(actualResult).isTrue()
    }
}