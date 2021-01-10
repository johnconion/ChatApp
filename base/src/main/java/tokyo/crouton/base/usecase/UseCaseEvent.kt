package tokyo.crouton.base.usecase

import tokyo.crouton.base.UseCase

sealed class UseCaseEvent {
    data class Success<T : Class<UseCase>>(val type: T) : UseCaseEvent()
    data class Failure<T : Class<UseCase>>(val type: T, val error: Throwable) : UseCaseEvent()
}