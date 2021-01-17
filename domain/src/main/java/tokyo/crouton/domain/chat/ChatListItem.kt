package tokyo.crouton.domain.chat

import java.util.Date

sealed class ChatListItem(open val chatId: ChatId, open val postedAt: Date) {
    data class MessagePost(
        override val chatId: ChatId,
        override val postedAt: Date,
        val message: String,
        val isMe: Boolean
    ) :
        ChatListItem(chatId, postedAt)
}