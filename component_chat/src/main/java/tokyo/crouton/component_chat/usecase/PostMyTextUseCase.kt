package tokyo.crouton.component_chat.usecase

import io.reactivex.Single
import io.realm.Realm
import tokyo.crouton.base.UseCase1
import tokyo.crouton.base.UseCaseDispatcher
import tokyo.crouton.datasource_realm.RealmChat
import javax.inject.Inject

class PostMyTextUseCase @Inject constructor(
    override val useCaseDispatcher: UseCaseDispatcher
) : UseCase1<String> {
    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun build(text: String): Single<Unit> = Single.fromCallable {
        val realm = Realm.getDefaultInstance()
        val chat = RealmChat().apply {
            this.text = text
        }
        realm.executeTransaction {
            it.insert(chat)
        }
    }
}