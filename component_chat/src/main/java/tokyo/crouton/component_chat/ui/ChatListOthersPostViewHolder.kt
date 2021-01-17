package tokyo.crouton.component_chat.ui

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tokyo.crouton.base.inflate
import tokyo.crouton.component_chat.R

class ChatListOthersPostViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(parent.inflate(R.layout.chat_list_item_others_post, false)),
    ChatListMessagePostViewHolder {
    override val backgroundView: View by lazy { itemView.findViewById<View>(R.id.background_view) }
    override val messageText: TextView by lazy { itemView.findViewById<TextView>(R.id.message_text) }
    override val timeText: TextView by lazy { itemView.findViewById<TextView>(R.id.time_text) }
}