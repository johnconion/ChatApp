package tokyo.crouton.component_chat.ui

import android.content.Context
import dagger.hilt.android.qualifiers.ActivityContext
import tokyo.crouton.base.toTimeString
import tokyo.crouton.domain.chat.ChatListItem
import javax.inject.Inject

class ChatListMessagePostBinder @Inject constructor(
    @ActivityContext private val context: Context,
    private val actions: ChatListActions
) {
    fun bind(viewHolder: ChatListMessagePostViewHolder, item: ChatListItem.MessagePost) {
        viewHolder.messageText.text = item.message
        viewHolder.timeText.text = item.postedAt.toTimeString(context)

        viewHolder.backgroundView.setOnLongClickListener {
            actions.removePost()
            true
        }
    }
}