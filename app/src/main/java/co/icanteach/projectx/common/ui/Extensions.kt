package co.icanteach.projectx.common.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import co.icanteach.projectx.common.Resource
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


/**
 * http://kotlinextensions.com/
 */
fun <T : ViewDataBinding> ViewGroup?.inflate(@LayoutRes layoutId: Int, attachToParent: Boolean = true): T {
    return DataBindingUtil.inflate(LayoutInflater.from(this!!.context), layoutId, this, attachToParent)
}

fun <T> LiveData<T>.observeNonNull(owner: LifecycleOwner, observer: (t: T) -> Unit) {
    this.observe(owner, Observer {
        it?.let(observer)
    })
}

operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
    add(disposable)
}

fun Any?.runIfNull(block: () -> Unit) {
    if (this == null) block()
}

val String.Companion.EMPTY get() = ""

fun <T> Observable<Resource<T>>.handleNetworkError(): Observable<Resource<T>> {
    return onErrorReturn { Resource.Error(Exception(it)) } // TODO: Implement a proper error handler here
}