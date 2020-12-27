package tokyo.crouton.base.baseInterface

import io.reactivex.rxjava3.core.Observable

interface ArrayStore<T> {
    var values: List<T>
    fun updates(): Observable<List<T>>
}

fun <T> ArrayStore<T>.getSize(): Int = values.size

fun <T> ArrayStore<T>.get(index: Int): T = values[index]