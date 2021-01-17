package tokyo.crouton.component_chat.ui

import tokyo.crouton.domain.chat.ChatListItem
import javax.inject.Inject

class ChatListOthersPostItemBinder @Inject constructor() {
    fun bind(viewHolder: ChatListOthersPostViewHolder, item: ChatListItem.OthersPost) {
        viewHolder.testText.text = item.text
    }
}