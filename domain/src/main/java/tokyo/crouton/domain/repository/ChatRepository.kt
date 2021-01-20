package tokyo.crouton.domain.repository

import tokyo.crouton.domain.chat.PostId
import java.util.Date

interface ChatRepository {
    fun addPost(message: String, date: Date, isMe: Boolean)
    fun removePost(postId: PostId)
    fun removeAll()
}