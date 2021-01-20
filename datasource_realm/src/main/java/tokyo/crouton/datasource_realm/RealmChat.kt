package tokyo.crouton.datasource_realm

import io.realm.Realm
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.kotlin.where
import java.util.Date

open class RealmChat(
    @PrimaryKey
    var id: Int = createId(),
    var sentAt: Date = Date(),
    var isMe: Boolean = true,
    var message: String = "",
    var isRemoved: Boolean = false
) : RealmObject() {
    companion object {
        fun createId(): Int {
            val realm = Realm.getDefaultInstance()
            val size = realm.where<RealmChat>().findAll().size
            return if (size == 0) 0 else size + 1
        }
    }
}
