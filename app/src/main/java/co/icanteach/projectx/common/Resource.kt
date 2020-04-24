package co.icanteach.projectx.common

// references :
// https://developer.android.com/jetpack/docs/guide#addendum


sealed class Resource<out T> {
    class Success<T>(val data: T) : Resource<T>()
    class Error(val exception: Throwable) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}

inline fun <T, R> Resource<T>.map(transform: (T) -> R): Resource<R> {
    return when (this) {
        is Resource.Success -> Resource.Success(transform(data))
        is Resource.Error -> Resource.Error(exception)
        is Resource.Loading -> Resource.Loading
    }
}

sealed class Status {

    object Content : Status()

    data class Error(val exception: Throwable) : Status()

    object Loading : Status()
}