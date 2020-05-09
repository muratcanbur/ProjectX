/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package retrofit2.adapter.rxjava3

import io.reactivex.rxjava3.annotations.Nullable
import retrofit2.Response

/** The result of executing an HTTP request.  */
class Result<T> private constructor(
    private val response: @Nullable Response<T>?,
    private val error: @Nullable Throwable?
) {

    /**
     * The response received from executing an HTTP request. Only present when [.isError] is
     * false, null otherwise.
     */
    fun response(): @Nullable Response<T>? {
        return response
    }

    /**
     * The error experienced while attempting to execute an HTTP request. Only present when [ ][.isError] is true, null otherwise.
     *
     *
     * If the error is an [IOException] then there was a problem with the transport to the
     * remote server. Any other exception type indicates an unexpected failure and should be
     * considered fatal (configuration error, programming error, etc.).
     */
    fun error(): @Nullable Throwable? {
        return error
    }

    /** `true` if the request resulted in an error. See [.error] for the cause.  */
    fun isError(): Boolean {
        return error != null
    }

    companion object {
        // Guarding public API nullability.
        fun <T> error(error: Throwable?): Result<T> {
            if (error == null) throw NullPointerException("error == null")
            return Result(null, error)
        }

        // Guarding public API nullability.
        fun <T> response(response: Response<T>?): Result<T> {
            if (response == null) throw NullPointerException("response == null")
            return Result(response, null)
        }
    }

}