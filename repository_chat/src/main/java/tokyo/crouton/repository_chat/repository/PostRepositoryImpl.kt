package tokyo.crouton.repository_chat.repository

import io.realm.Realm
import tokyo.crouton.datasource_realm.RealmPost
import tokyo.crouton.domain.chat.PostId
import tokyo.crouton.domain.repository.PostRepository
import java.util.Date
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val realm: Realm
) : PostRepository {
    override fun addPost(message: String, date: Date, isMe: Boolean) =
        realm.executeTransaction {
            it.insert(
                RealmPost().apply {
                    this.message = message
                    this.isMe = isMe
                }
            )
        }

    override fun removePost(postId: PostId) {
        val value =
            realm.where(RealmPost::class.java)
                .findAll()
                .firstOrNull { it.id == postId.rawValue }
                ?: throw IllegalArgumentException("postId = ${postId.rawValue} is not found")
        realm.executeTransaction {
            value.isRemoved = true
        }
    }

    override fun removeAll() {
        val values =
            realm.where(RealmPost::class.java)
                .findAll()
        realm.executeTransaction {
            values.deleteAllFromRealm()
        }
    }
}