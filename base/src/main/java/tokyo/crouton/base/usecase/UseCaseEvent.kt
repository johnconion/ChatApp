package tokyo.crouton.base.usecase

sealed class UseCaseEvent(open val useCase: UseCase) {
    data class Success(override val useCase: UseCase) :
        UseCaseEvent(useCase)

    data class Failure(
        override val useCase: UseCase,
        val error: Throwable
    ) : UseCaseEvent(useCase)
}