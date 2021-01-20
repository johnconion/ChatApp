package tokyo.crouton.repository_chat.repository

import io.realm.Realm
import tokyo.crouton.datasource_realm.RealmChat
import tokyo.crouton.domain.chat.ChatId
import tokyo.crouton.domain.repository.ChatRepository
import java.util.Date
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val realm: Realm
) : ChatRepository {
    override fun addPost(message: String, date: Date, isMe: Boolean) =
        realm.executeTransaction {
            it.insert(
                RealmChat().apply {
                    this.message = message
                    this.isMe = isMe
                }
            )
        }

    override fun removePost(chatId: ChatId) {
        val value =
            realm.where(RealmChat::class.java)
                .findAll()
                .firstOrNull { it.id == chatId.rawValue }
                ?: throw IllegalArgumentException("chatId = ${chatId.rawValue} is not found")
        realm.executeTransaction {
            value.isRemoved = true
        }
    }

    override fun removeAll() {
        val values =
            realm.where(RealmChat::class.java)
                .findAll()
        realm.executeTransaction {
            values.deleteAllFromRealm()
        }
    }
}