package co.icanteach.projectx.common

import androidx.annotation.NonNull

// references :
// https://developer.android.com/jetpack/docs/guide#addendum

class Resource<out T> constructor(val status: Status, val data: T?, val error: Throwable? = null) {

    companion object {

        fun <T> success(@NonNull data: T): Resource<T> {
            return Resource(Status.SUCCESS, data)
        }

        fun <T> error(throwable: Throwable): Resource<T> {
            return Resource(status = Status.ERROR, data = null, error = throwable)
        }

        fun <T> loading(): Resource<T> = Resource(Status.LOADING, null)
    }
}

suspend fun <T> runWithCatching(block: suspend () -> Resource<T>) = try {
    block()
} catch (e: Exception) {
    Resource(
        status = Status.ERROR,
        data = null,
        error = e
    )
}