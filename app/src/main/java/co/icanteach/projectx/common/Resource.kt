package co.icanteach.projectx.common

import androidx.annotation.NonNull

// references :
// https://developer.android.com/jetpack/docs/guide#addendum

class Resource<T> private constructor(val status: Status, val data: T?) {

    companion object {

        fun <T> success(@NonNull data: T): Resource<T> {
            return Resource(Status.SUCCESS, data)
        }

        fun <T> error(msg: String): Resource<T> {
            return Resource(Status.ERROR, null)
        }

        fun <T> loading(): Resource<T> = Resource(Status.LOADING, null)
    }
}