package tokyo.crouton.datasource_realm

import io.realm.Realm
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.kotlin.where
import java.util.Date

class RealmChat : RealmObject() {
    @PrimaryKey
    var id: Int = createId()
    var sentAt: Date = Date()
    var isMe: Boolean = true
    var text: String = ""

    companion object {
        fun createId(): Int {
            val realm = Realm.getDefaultInstance()
            val size = realm.where<RealmChat>().findAll().size
            return if (size == 0) 0 else size + 1
        }
    }
}
