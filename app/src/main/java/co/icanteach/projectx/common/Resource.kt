package co.icanteach.projectx.common

sealed class Resource<T> {
    class Success<T>(val data: T) : Resource<T>()
    class Error<T>(val exception: Exception) : Resource<T>()
    class Loading<T> : Resource<T>()

    fun <T2> map(transform: (T) -> T2): Resource<T2> {
        return when (this) {
            is Success -> Success(transform(data))
            is Error -> Error(exception)
            is Loading -> Loading()
        }
    }

    fun <T2, Z> zipWith(anotherResource: Resource<T2>, transform: (T, T2) -> Z): Resource<Z> {
        return when {
            this is Success && anotherResource is Success -> Success(transform(data, anotherResource.data))
            this is Error -> Error(exception)
            anotherResource is Error -> Error(anotherResource.exception)
            else -> Loading()
        }
    }
}