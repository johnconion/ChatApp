package tokyo.crouton.component_chat.usecase

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import tokyo.crouton.base.usecase.UseCase1
import tokyo.crouton.base.usecase.UseCaseDispatcher
import tokyo.crouton.domain.repository.PostRepository
import tokyo.crouton.network.APIClient
import tokyo.crouton.network.api.ChatApi
import java.util.Date
import javax.inject.Inject

class PostMyTextUseCase @Inject constructor(
    override val useCaseDispatcher: UseCaseDispatcher,
    private val APIClient: APIClient,
    private val postRepository: PostRepository
) : UseCase1<String> {
    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun build(text: String): Single<Unit> =
        Single.fromCallable {
            postRepository.addPost(text, Date(), true)
        }
            .observeOn(Schedulers.io())
            .flatMap {
                APIClient.create(ChatApi::class.java).postMessage()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                // it.date.toDateFromISOString() としたらなぜか返ってきた Date がAPIを叩いた時刻より前になってしまった
                // 端末の時刻とサーバーの時刻にズレがあるっぽいので諦めて Date() に置き換えた
                postRepository.addPost(it.message, Date(), false)
            }.map { Unit }
}