package tokyo.crouton.component_chat.usecase

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import tokyo.crouton.base.UseCase1
import tokyo.crouton.base.UseCaseDispatcher
import tokyo.crouton.datasource_realm.RealmChat
import tokyo.crouton.network.APIClient
import tokyo.crouton.network.api.ChatApi
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAccessor
import java.util.Date
import javax.inject.Inject

class PostMyTextUseCase @Inject constructor(
    override val useCaseDispatcher: UseCaseDispatcher,
    private val realm: Realm,
    private val APIClient: APIClient
) : UseCase1<String> {
    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun build(text: String): Single<Unit> =
        Single.fromCallable {
            val myPost = RealmChat().apply {
                this.text = text
                isMe = true
            }
            realm.executeTransaction {
                it.insert(myPost)
            }
        }
            .observeOn(Schedulers.io())
            .flatMap {
                APIClient.builder.create(ChatApi::class.java).postMessage()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                val botResponse = RealmChat().apply {
                    this.text = it.message
                    isMe = false
                    val timeFormatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME
                    val accessor: TemporalAccessor = timeFormatter.parse(it.date)
                    sentAt = Date.from(Instant.from(accessor))
                }
                realm.executeTransaction {
                    it.insert(botResponse)
                }
            }.map { Unit }
}