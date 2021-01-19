package tokyo.crouton.base.usecase

import io.reactivex.Observable
import tokyo.crouton.base.usecase.UseCaseEvent.Failure
import tokyo.crouton.base.usecase.UseCaseEvent.Success

fun UseCase.events(): Observable<UseCaseEvent> = this.useCaseDispatcher.events().filter {
    when (it) {
        is Success -> it.useCase == this
        is Failure -> it.useCase == this
    }
}

val UseCaseEvent.isSuccessful get() = this is Success
val UseCaseEvent.isFailed get() = this is Failure

fun Observable<UseCaseEvent>.filterIsSuccessful(): Observable<Success> =
    this.filter { it.isSuccessful }.cast(Success::class.java)

fun Observable<UseCaseEvent>.filterIsFailure(): Observable<Failure> =
    this.filter { it.isFailed }.cast(Failure::class.java)