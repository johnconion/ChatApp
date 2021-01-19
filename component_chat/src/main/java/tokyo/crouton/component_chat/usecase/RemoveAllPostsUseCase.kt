package tokyo.crouton.component_chat.usecase

import io.reactivex.Single
import io.realm.Realm
import tokyo.crouton.base.usecase.UseCase0
import tokyo.crouton.base.usecase.UseCaseDispatcher
import tokyo.crouton.domain.repository.ChatRepository
import javax.inject.Inject

class RemoveAllPostsUseCase @Inject constructor(
    override val useCaseDispatcher: UseCaseDispatcher,
    private val realm: Realm,
    private val chatRepository: ChatRepository
) : UseCase0 {
    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun build(): Single<Unit> =
        Single.fromCallable {
            chatRepository.removeAll()
        }.map { Unit }
}