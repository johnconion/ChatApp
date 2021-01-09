package tokyo.crouton.component_chat.ui

import tokyo.crouton.domain.chat.ChatListItem
import javax.inject.Inject

class ChatListMyPostItemBinder @Inject constructor() {
    fun bind(viewHolder: ChatListMyPostViewHolder, item: ChatListItem.MyPost) {
        viewHolder.testText.text = item.text
    }
}