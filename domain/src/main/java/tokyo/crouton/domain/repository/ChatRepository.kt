package tokyo.crouton.domain.repository

import tokyo.crouton.domain.chat.ChatId
import java.util.Date

interface ChatRepository {
    fun addPost(message: String, date: Date, isMe: Boolean)
    fun removePost(chatId: ChatId)
}