package tokyo.crouton.component_chat.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dagger.Lazy
import tokyo.crouton.base.baseInterface.get
import tokyo.crouton.domain.chat.ChatListItem.MyPost
import tokyo.crouton.domain.store.ChatListItemsStore
import javax.inject.Inject

class ChatListAdapter @Inject constructor(
    private val chatListItemsStore: ChatListItemsStore,
    private val chatListMyPostItemBinder: Lazy<ChatListMyPostItemBinder>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

//    init {
//        chatListItemsStore.updates().subscribe(notify()).dispose()
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ChatListMyPostViewHolder(parent)

    override fun getItemCount(): Int = chatListItemsStore.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as ChatListMyPostViewHolder
        chatListMyPostItemBinder.get().bind(viewHolder, chatListItemsStore.get(position) as MyPost)
    }

}