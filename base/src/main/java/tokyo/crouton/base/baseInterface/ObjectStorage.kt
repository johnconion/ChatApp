package tokyo.crouton.base.baseInterface

import com.jakewharton.rxrelay3.BehaviorRelay

interface ObjectStorage<T> {
    var relay: BehaviorRelay<T>
}

fun <T> ObjectStorage<T>.setData(v: T) = relay.accept(v)

fun <T> ObjectStorage<T>.getData(): T = relay.value

fun <T> ObjectStorage<T>.update(): BehaviorRelay<T> = relay
