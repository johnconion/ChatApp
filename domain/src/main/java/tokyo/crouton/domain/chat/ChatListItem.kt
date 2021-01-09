package tokyo.crouton.domain.chat

import java.util.Date

sealed class ChatListItem {
    data class MyPost(val chatId: ChatId, val text: String, val postAt: Date) : ChatListItem()
}