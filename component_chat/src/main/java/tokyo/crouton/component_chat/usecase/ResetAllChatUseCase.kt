package tokyo.crouton.component_chat.usecase

import io.reactivex.Single
import tokyo.crouton.base.usecase.UseCase0
import tokyo.crouton.base.usecase.UseCaseDispatcher
import tokyo.crouton.domain.repository.PostRepository
import javax.inject.Inject

class ResetAllChatUseCase @Inject constructor(
    override val useCaseDispatcher: UseCaseDispatcher,
    private val postRepository: PostRepository
) : UseCase0 {
    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun build(): Single<Unit> =
        Single.fromCallable {
            postRepository.removeAll()
        }.map { Unit }
}