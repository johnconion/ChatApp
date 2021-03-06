package tokyo.crouton.repository_chat.store

import io.reactivex.Observable
import tokyo.crouton.base.baseInterface.ArrayStore.Event
import tokyo.crouton.base.onMainThread
import tokyo.crouton.datasource_realm.RealmManager
import tokyo.crouton.datasource_realm.RealmPost
import tokyo.crouton.domain.chat.ChatListItem
import tokyo.crouton.domain.chat.PostId
import tokyo.crouton.domain.store.ChatListItemsStore
import javax.inject.Inject

class ChatListItemsStoreImpl @Inject constructor() : ChatListItemsStore {

    override val size: Int get() = items.size

    private var items: List<ChatListItem> = listOf()

    override val values: List<ChatListItem> get() = items

    override fun get(index: Int): ChatListItem = values[index]

    override fun updates(): Observable<Event> =
        RealmManager.createObservable(RealmPost::class.java)
            .map { it.toItems() }
            .onMainThread()
            .doOnNext { items = it }
            .map { Event.DataSetChanged }

    private fun List<RealmPost>.toItems(): List<ChatListItem> =
        this.sortedBy { it.sentAt }
            .map {
                if (it.isRemoved)
                    ChatListItem.RemovedPost
                else
                    ChatListItem.MessagePost(PostId(it.id), it.sentAt, it.message, it.isMe)
            }
}