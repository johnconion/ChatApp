package tokyo.crouton.component_chat.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dagger.Lazy
import tokyo.crouton.base.AutoDisposable
import tokyo.crouton.base.AutoDisposableDelegation
import tokyo.crouton.base.notify
import tokyo.crouton.base.requireNotNull
import tokyo.crouton.component_chat.ui.ChatListAdapter.ViewType.MY_MESSAGE
import tokyo.crouton.component_chat.ui.ChatListAdapter.ViewType.OTHERS_MESSAGE
import tokyo.crouton.component_chat.ui.ChatListAdapter.ViewType.REMOVED
import tokyo.crouton.domain.chat.ChatListItem.MessagePost
import tokyo.crouton.domain.chat.ChatListItem.RemovedPost
import tokyo.crouton.domain.store.ChatListItemsStore
import javax.inject.Inject

class ChatListAdapter @Inject constructor(
    private val chatListItemsStore: ChatListItemsStore,
    private val chatListMessagePostBinder: Lazy<ChatListMessagePostBinder>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), AutoDisposable by AutoDisposableDelegation() {

    init {
        chatListItemsStore.updates().subscribe(notify()).autoDispose()
    }

    override fun getItemViewType(position: Int): Int =
        when (val item = chatListItemsStore.get(position)) {
            is MessagePost -> {
                if (item.isMe) MY_MESSAGE.value else OTHERS_MESSAGE.value
            }
            RemovedPost -> REMOVED.value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (ViewType.of(viewType)) {
            MY_MESSAGE -> ChatListMyPostViewHolder(parent)
            OTHERS_MESSAGE -> ChatListOthersPostViewHolder(parent)
            REMOVED -> ChatListRemovedPostViewHolder(parent)
        }

    override fun getItemCount(): Int = chatListItemsStore.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when (val item = chatListItemsStore.get(position)) {
            is MessagePost -> chatListMessagePostBinder.get()
                .bind(holder as ChatListMessagePostViewHolder, item)
            RemovedPost -> Unit
        }

    private enum class ViewType {
        MY_MESSAGE,
        OTHERS_MESSAGE,
        REMOVED;

        val value: Int = ordinal

        companion object {
            fun of(value: Int): ViewType =
                values().find { it.value == value }.requireNotNull { "value is $value" }
        }
    }
}