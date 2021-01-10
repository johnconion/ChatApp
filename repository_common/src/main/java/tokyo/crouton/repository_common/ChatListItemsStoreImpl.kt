package tokyo.crouton.repository_common

import io.reactivex.Observable
import tokyo.crouton.base.baseInterface.ArrayStore.Event
import tokyo.crouton.base.onMainThread
import tokyo.crouton.datasource_realm.RealmChat
import tokyo.crouton.datasource_realm.RealmManager
import tokyo.crouton.domain.chat.ChatId
import tokyo.crouton.domain.chat.ChatListItem
import tokyo.crouton.domain.store.ChatListItemsStore
import javax.inject.Inject

class ChatListItemsStoreImpl @Inject constructor() : ChatListItemsStore {

    override val size: Int get() = items.size

    private var items: List<ChatListItem> = listOf()

    override val values: List<ChatListItem> get() = items

    override fun get(index: Int): ChatListItem = values[index]

    override fun updates(): Observable<Event> =
        RealmManager.createObservable(RealmChat::class.java)
            .map { it.toItems() }
            .onMainThread()
            .doOnNext { items = it }
            .map { Event.DataSetChanged }

    private fun List<RealmChat>.toItems(): List<ChatListItem> =
        this.sortedBy { it.id }.map { ChatListItem.MyPost(ChatId(it.id), it.text, it.sentAt) }
}