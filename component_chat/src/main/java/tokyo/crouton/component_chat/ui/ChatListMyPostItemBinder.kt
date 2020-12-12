package tokyo.crouton.component_chat.ui

import tokyo.crouton.domain.ChatListMyPostItem
import javax.inject.Inject

class ChatListMyPostItemBinder @Inject constructor() {
    fun bind(viewHolder: ChatListMyPostViewHolder, item: ChatListMyPostItem) {
        viewHolder.testText.text = item.text
    }
}