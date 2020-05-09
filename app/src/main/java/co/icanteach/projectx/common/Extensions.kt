package co.icanteach.projectx.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers


/**
 * http://kotlinextensions.com/
 */
fun <T : ViewDataBinding> ViewGroup?.inflate(@LayoutRes layoutId: Int, attachToParent: Boolean = true): T {
    return DataBindingUtil.inflate(
        LayoutInflater.from(this!!.context),
        layoutId,
        this,
        attachToParent
    )
}

fun <T> Observable<T>.remote(): Observable<Resource<T>> =
    map<Resource<T>> { Resource.Success(it) }
        .onErrorReturn { throwable ->
            Resource.Error(throwable)
        }
        .subscribeOn(Schedulers.io())

fun <T> LiveData<T>.observeNonNull(owner: LifecycleOwner, observer: (t: T) -> Unit) {
    this.observe(owner, Observer {
        it?.let(observer)
    })
}

fun <T> Observable<Resource<T>>.doOnSuccess(
    onSuccess: (T) -> (Unit)
): Observable<Resource<T>> {
    return this.doOnNext {
        if (it is Resource.Success) {
            onSuccess(it.data)
        }
    }

}

operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
    add(disposable)
}

fun Any?.runIfNull(block: () -> Unit) {
    if (this == null) block()
}
