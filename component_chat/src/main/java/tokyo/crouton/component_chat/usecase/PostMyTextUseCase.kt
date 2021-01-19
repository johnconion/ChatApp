package tokyo.crouton.component_chat.usecase

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import tokyo.crouton.base.toDateFromISOString
import tokyo.crouton.base.usecase.UseCase1
import tokyo.crouton.base.usecase.UseCaseDispatcher
import tokyo.crouton.domain.repository.ChatRepository
import tokyo.crouton.network.APIClient
import tokyo.crouton.network.api.ChatApi
import java.util.Date
import javax.inject.Inject

class PostMyTextUseCase @Inject constructor(
    override val useCaseDispatcher: UseCaseDispatcher,
    private val APIClient: APIClient,
    private val chatRepository: ChatRepository
) : UseCase1<String> {
    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun build(text: String): Single<Unit> =
        Single.fromCallable {
            chatRepository.addPost(text, Date(), true)
        }
            .observeOn(Schedulers.io())
            .flatMap {
                APIClient.create(ChatApi::class.java).postMessage()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                chatRepository.addPost(it.message, it.date.toDateFromISOString(), false)
            }.map { Unit }
}