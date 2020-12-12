package tokyo.crouton.component_chat.ui

import javax.inject.Inject

class ChatListMyPostItemBinder @Inject constructor() {
    fun bind(viewHolder: ChatListMyPostViewHolder) {
        viewHolder.testText.text = "TEST TEXT"
    }
}