package tokyo.crouton.component_chat.ui

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tokyo.crouton.base.inflate
import tokyo.crouton.component_chat.R

class ChatListMyPostViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(parent.inflate(R.layout.chat_list_item_my_post, false)) {
    val testText: TextView by lazy { itemView.findViewById<TextView>(R.id.test_text) }
}