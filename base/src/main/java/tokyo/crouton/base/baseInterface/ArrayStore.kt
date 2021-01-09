package tokyo.crouton.base.baseInterface

import io.reactivex.Observable

interface ArrayStore<T> {
    val size: Int
    val values: List<T>
    fun get(index: Int): T
    fun updates(): Observable<Event>

    sealed class Event {
        object DataSetChanged : Event()
        data class ItemChanged(val position: Int) : Event()
        data class ItemRangeChanged(val range: IntRange) : Event()
        data class ItemInserted(val position: Int) : Event()
        data class ItemMoved(val from: Int, val to: Int) : Event()
        data class ItemRangeInserted(val range: IntRange) : Event()
        data class ItemRemoved(val position: Int) : Event()
        data class ItemRangeRemoved(val range: IntRange) : Event()

        fun shiftPosition(offset: Int): Event = when (this) {
            is DataSetChanged -> this
            is ItemInserted -> ItemInserted(position + offset)
            is ItemChanged -> ItemChanged(position + offset)
            is ItemRangeChanged -> ItemRangeChanged((range.start + offset)..(range.endInclusive + offset))
            is ArrayStore.Event.ItemRemoved -> ItemRemoved(position + offset)
            is ArrayStore.Event.ItemMoved -> ItemMoved(from + offset, to + offset)
            is ArrayStore.Event.ItemRangeInserted -> ItemRangeInserted((range.start + offset)..(range.endInclusive + offset))
            is ArrayStore.Event.ItemRangeRemoved -> ItemRangeRemoved((range.start + offset)..(range.endInclusive + offset))
        }
    }
}