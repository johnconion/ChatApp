package tokyo.crouton.base.usecase

import com.jakewharton.rxrelay2.PublishRelay
import tokyo.crouton.base.usecase.UseCaseEvent.Success
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UseCaseDispatcher @Inject constructor() {

    private val relay: PublishRelay<UseCaseEvent> = PublishRelay.create()

    fun events(): PublishRelay<UseCaseEvent> = relay

    fun <T : Class<UseCase>> flushSuccessEvent(UseCaseType: T) {
        relay.accept(Success(UseCaseType))
    }

    fun <T : Class<UseCase>> flushFailureEvent(UseCaseType: T, error: Throwable) {
        relay.accept(UseCaseEvent.Failure(UseCaseType, error))
    }
}