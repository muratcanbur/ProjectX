package co.icanteach.projectx.ui.populartvshows

import co.icanteach.projectx.common.Status

class PopularTVShowsStatusViewState(
    val status: Status
) {
    fun isLoading() = status is Status.Loading

    fun getErrorMessage() = if (status is Status.Error) status.exception.message else ""

    fun shouldShowErrorMessage() = status is Status.Error
}