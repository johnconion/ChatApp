package tokyo.crouton.base.usecase

import android.annotation.SuppressLint
import io.reactivex.Single

interface UseCase {
    val useCaseDispatcher: UseCaseDispatcher
}

interface UseCase0 : UseCase {
    fun build(): Single<Unit>
}

@SuppressLint("CheckResult")
fun UseCase0.execute() {
    build().subscribe(
        {
            useCaseDispatcher.flushSuccessEvent((this as UseCase).javaClass)
        }, {
            useCaseDispatcher.flushFailureEvent(
                (this as UseCase).javaClass,
                it
            )
        }
    )
}

interface UseCase1<T> : UseCase {
    fun build(arg: T): Single<Unit>
}

@SuppressLint("CheckResult")
fun <T> UseCase1<T>.execute(arg: T) {
    build(arg).subscribe(
        {
            useCaseDispatcher.flushSuccessEvent((this as UseCase).javaClass)
        }, {
            useCaseDispatcher.flushFailureEvent(
                (this as UseCase).javaClass,
                it
            )
        }
    )
}
