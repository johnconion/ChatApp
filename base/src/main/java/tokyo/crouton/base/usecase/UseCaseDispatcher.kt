package tokyo.crouton.base.usecase

import com.jakewharton.rxrelay2.PublishRelay
import tokyo.crouton.base.usecase.UseCaseEvent.Success
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UseCaseDispatcher @Inject constructor() {

    private val relay: PublishRelay<UseCaseEvent> = PublishRelay.create()

    fun events(): PublishRelay<UseCaseEvent> = relay

    fun flushSuccessEvent(useCase: UseCase) =
        relay.accept(Success(useCase))

    fun flushFailureEvent(useCase: UseCase, error: Throwable) =
        relay.accept(UseCaseEvent.Failure(useCase, error))
}