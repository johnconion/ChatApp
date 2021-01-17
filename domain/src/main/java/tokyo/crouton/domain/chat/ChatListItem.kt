package tokyo.crouton.domain.chat

import java.util.Date

sealed class ChatListItem(open val chatId: ChatId, open val postAt: Date) {
    data class MyPost(
        override val chatId: ChatId,
        override val postAt: Date,
        val text: String
    ) :
        ChatListItem(chatId, postAt)

    data class OthersPost(
        override val chatId: ChatId,
        override val postAt: Date,
        val text: String
    ) : ChatListItem(chatId, postAt)
}