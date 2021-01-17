package tokyo.crouton.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import tokyo.crouton.base.baseInterface.ArrayStore.Event
import tokyo.crouton.base.baseInterface.ArrayStore.Event.DataSetChanged
import tokyo.crouton.base.baseInterface.ArrayStore.Event.ItemChanged
import tokyo.crouton.base.baseInterface.ArrayStore.Event.ItemInserted
import tokyo.crouton.base.baseInterface.ArrayStore.Event.ItemMoved
import tokyo.crouton.base.baseInterface.ArrayStore.Event.ItemRangeChanged
import tokyo.crouton.base.baseInterface.ArrayStore.Event.ItemRangeInserted
import tokyo.crouton.base.baseInterface.ArrayStore.Event.ItemRangeRemoved
import tokyo.crouton.base.baseInterface.ArrayStore.Event.ItemRemoved

fun <T : Any> T?.requireNotNull(): T = requireNotNull(this)

fun <T : Any> T?.requireNotNull(lazyMessage: () -> Any): T = requireNotNull(this, lazyMessage)

fun ViewGroup.inflate(resource: Int, attachToRoot: Boolean): View =
    LayoutInflater.from(context).inflate(resource, this, attachToRoot)

val <T> T.checkAllMatched: T get() = this

fun <T> Observable<T>.onMainThread(): Observable<T> = observeOn(AndroidSchedulers.mainThread())

fun <VH : RecyclerView.ViewHolder> RecyclerView.Adapter<VH>.notify(): Consumer<Event> =
    Consumer {
        when (it) {
            is ItemInserted -> this.notifyItemInserted(it.position)
            is DataSetChanged -> this.notifyDataSetChanged()
            is ItemChanged -> this.notifyItemChanged(it.position)
            is ItemRangeChanged -> this.notifyItemRangeChanged(
                it.range.start,
                it.range.count()
            )
            is ItemRemoved -> this.notifyItemRemoved(it.position)
            is ItemMoved -> this.notifyItemMoved(it.from, it.to)
            is ItemRangeInserted -> this.notifyItemRangeInserted(
                it.range.start,
                it.range.count()
            )
            is ItemRangeRemoved -> this.notifyItemRangeRemoved(
                it.range.start,
                it.range.count()
            )
        }.checkAllMatched
    }