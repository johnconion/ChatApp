package tokyo.crouton.base.baseInterface

import io.reactivex.rxjava3.core.Observable

interface ObjectStore<T> {
    var value: T
    fun updates(): Observable<T>
}

