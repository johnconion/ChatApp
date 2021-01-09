package tokyo.crouton.base.baseInterface

import io.reactivex.Observable

interface ObjectStore<T> {
    var value: T
    fun updates(): Observable<T>
}

