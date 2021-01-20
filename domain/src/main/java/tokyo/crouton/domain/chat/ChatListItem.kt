package tokyo.crouton.domain.chat

import java.util.Date

sealed class ChatListItem {
    data class MessagePost(
        val postId: PostId,
        val postedAt: Date,
        val message: String,
        val isMe: Boolean
    ) :
        ChatListItem()

    object RemovedPost : ChatListItem()
}