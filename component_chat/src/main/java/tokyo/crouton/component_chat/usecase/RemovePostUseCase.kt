package tokyo.crouton.component_chat.usecase

import io.reactivex.Single
import tokyo.crouton.base.usecase.UseCase1
import tokyo.crouton.base.usecase.UseCaseDispatcher
import tokyo.crouton.domain.chat.PostId
import tokyo.crouton.domain.repository.ChatRepository
import javax.inject.Inject

class RemovePostUseCase @Inject constructor(
    override val useCaseDispatcher: UseCaseDispatcher,
    private val chatRepository: ChatRepository
) : UseCase1<PostId> {
    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun build(postId: PostId): Single<Unit> =
        Single.fromCallable {
            chatRepository.removePost(postId)
        }.map { Unit }
}