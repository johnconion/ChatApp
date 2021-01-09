package tokyo.crouton.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

interface AutoDisposable {
    fun Disposable.autoDispose()
    fun Disposable.autoDispose(key: String?)
    fun dispose()
    fun dispose(key: String? = null)
    fun addChild(child: AutoDisposable)
    fun add(disposable: Disposable)
    fun add(disposable: Disposable, key: String? = null)
}

class AutoDisposableDelegation(vararg children: AutoDisposable) : AutoDisposable {
    private val disposables = mutableMapOf<String?, CompositeDisposable>()
    private val nestedAutoDispose = arrayListOf(*children)

    private fun compositeDisposableForKey(key: String?) = disposables[key]
        ?: CompositeDisposable().apply { disposables[key] = this }

    override fun Disposable.autoDispose() = autoDispose(null)
    override fun Disposable.autoDispose(key: String?) =
        compositeDisposableForKey(key).add(this).let { Unit }

    override fun dispose() = dispose(null)
    override fun dispose(key: String?) {
        if (key == null) {
            disposables.values.forEach { it.dispose() }
            disposables.clear()
        } else {
            disposables.remove(key)?.dispose()
        }

        nestedAutoDispose.forEach {
            it.dispose(key)
        }
    }

    override fun addChild(child: AutoDisposable) = nestedAutoDispose.add(child).let { Unit }

    override fun add(disposable: Disposable) = add(disposable, null)
    override fun add(disposable: Disposable, key: String?) =
        compositeDisposableForKey(key).add(disposable).let { Unit }
}