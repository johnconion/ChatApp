package tokyo.crouton.component_chat.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dagger.Lazy
import tokyo.crouton.base.AutoDisposable
import tokyo.crouton.base.AutoDisposableDelegation
import tokyo.crouton.base.notify
import tokyo.crouton.base.requireNotNull
import tokyo.crouton.component_chat.ui.ChatListAdapter.ViewType.MY_POST
import tokyo.crouton.component_chat.ui.ChatListAdapter.ViewType.OTHERS_POST
import tokyo.crouton.domain.chat.ChatListItem.MyPost
import tokyo.crouton.domain.chat.ChatListItem.OthersPost
import tokyo.crouton.domain.store.ChatListItemsStore
import javax.inject.Inject

class ChatListAdapter @Inject constructor(
    private val chatListItemsStore: ChatListItemsStore,
    private val chatListMyPostItemBinder: Lazy<ChatListMyPostItemBinder>,
    private val chatListOthersPostItemBinder: Lazy<ChatListOthersPostItemBinder>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), AutoDisposable by AutoDisposableDelegation() {

    init {
        chatListItemsStore.updates().subscribe(notify()).autoDispose()
    }

    override fun getItemViewType(position: Int): Int =
        when (chatListItemsStore.get(position)) {
            is MyPost -> MY_POST.value
            is OthersPost -> OTHERS_POST.value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (ViewType.of(viewType)) {
            MY_POST -> ChatListMyPostViewHolder(parent)
            OTHERS_POST -> ChatListOthersPostViewHolder(parent)
        }

    override fun getItemCount(): Int = chatListItemsStore.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when (val item = chatListItemsStore.get(position)) {
            is MyPost -> chatListMyPostItemBinder.get()
                .bind(holder as ChatListMyPostViewHolder, item)
            is OthersPost -> chatListOthersPostItemBinder.get()
                .bind(holder as ChatListOthersPostViewHolder, item)
        }

    private enum class ViewType {
        MY_POST,
        OTHERS_POST;

        val value: Int = ordinal

        companion object {
            fun of(value: Int): ViewType =
                values().find { it.value == value }.requireNotNull { "value is $value" }
        }
    }
}