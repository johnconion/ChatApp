package tokyo.crouton.component_chat.ui

import tokyo.crouton.domain.chat.ChatListItem
import javax.inject.Inject

class ChatListMessagePostBinder @Inject constructor(
    private val actions: ChatListActions
) {
    fun bind(viewHolder: ChatListMessagePostViewHolder, item: ChatListItem.MessagePost) {
        viewHolder.messageText.text = item.message
        viewHolder.timeText.text = "19:25" // item.postedAt

        viewHolder.backgroundView.setOnLongClickListener {
            actions.removePost()
            true
        }
    }
}