package tokyo.crouton.repository_common

import io.reactivex.Observable
import tokyo.crouton.base.baseInterface.ArrayStore.Event
import tokyo.crouton.domain.chat.ChatId
import tokyo.crouton.domain.chat.ChatListItem
import tokyo.crouton.domain.store.ChatListItemsStore
import java.util.Date
import javax.inject.Inject

class ChatListItemsStoreImpl @Inject constructor() : ChatListItemsStore {

    override val size: Int get() = items.size

    private var items: List<ChatListItem> = listOf(
        ChatListItem.MyPost(ChatId(0), "aaaaa", Date()),
        ChatListItem.MyPost(ChatId(1), "bbbbb", Date())
    )

    override var values: List<ChatListItem> = items

    override fun updates(): Observable<Event> {
        TODO("Not yet implemented")
    }
}