package tokyo.crouton.component_chat.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dagger.Lazy
import tokyo.crouton.domain.ChatId
import tokyo.crouton.domain.ChatListMyPostItem
import java.util.Date
import javax.inject.Inject

class ChatListAdapter @Inject constructor(
    private val chatListMyPostItemBinder: Lazy<ChatListMyPostItemBinder>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val dummyData: List<ChatListMyPostItem> = listOf(
        ChatListMyPostItem(ChatId(0), "あいうえお", Date()),
        ChatListMyPostItem(ChatId(1), "かきくけこ", Date()),
        ChatListMyPostItem(ChatId(2), "さしすせそ", Date()),
        ChatListMyPostItem(ChatId(3), "たちつてと", Date()),
        ChatListMyPostItem(ChatId(4), "なにぬねの", Date()),
        ChatListMyPostItem(ChatId(5), "はひふへほ", Date()),
        ChatListMyPostItem(ChatId(6), "まみむめも", Date()),
        ChatListMyPostItem(ChatId(7), "やゆよ", Date()),
        ChatListMyPostItem(ChatId(8), "らりるれろ", Date()),
        ChatListMyPostItem(ChatId(9), "わをん", Date())
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ChatListMyPostViewHolder(parent)

    override fun getItemCount(): Int = dummyData.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as ChatListMyPostViewHolder
        chatListMyPostItemBinder.get().bind(viewHolder, dummyData[position])
    }

}