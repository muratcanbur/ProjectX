/*
 * Copyright (C) 2016 Jake Wharton
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

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.exceptions.CompositeException
import io.reactivex.rxjava3.exceptions.Exceptions
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import retrofit2.Response

internal class ResultObservable<T>(private val upstream: Observable<Response<T>>) :
    Observable<Result<T>?>() {
    override fun subscribeActual(observer: Observer<in Result<T>?>) {
        upstream.subscribe(ResultObserver(observer))
    }

    private class ResultObserver<R> internal constructor(private val observer: Observer<in Result<R>?>) :
        Observer<Response<R>?> {
        override fun onSubscribe(disposable: Disposable) {
            observer.onSubscribe(disposable)
        }

        override fun onNext(response: Response<R>?) {
            observer.onNext(Result.response(response))
        }

        override fun onError(throwable: Throwable) {
            try {
                observer.onNext(Result.error(throwable))
            } catch (t: Throwable) {
                try {
                    observer.onError(t)
                } catch (inner: Throwable) {
                    Exceptions.throwIfFatal(inner)
                    RxJavaPlugins.onError(CompositeException(t, inner))
                }
                return
            }
            observer.onComplete()
        }

        override fun onComplete() {
            observer.onComplete()
        }

    }

}