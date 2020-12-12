package tokyo.crouton.component_chat.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dagger.Lazy
import javax.inject.Inject

class ChatListAdapter @Inject constructor(
    private val chatListMyPostItemBinder: Lazy<ChatListMyPostItemBinder>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ChatListMyPostViewHolder(parent)

    override fun getItemCount(): Int = 10

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as ChatListMyPostViewHolder
        chatListMyPostItemBinder.get().bind(viewHolder)
    }

}