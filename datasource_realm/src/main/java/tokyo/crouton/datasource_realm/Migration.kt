package tokyo.crouton.datasource_realm

import io.realm.DynamicRealm
import io.realm.RealmMigration

class Migration : RealmMigration {
    override fun migrate(realm: DynamicRealm, oldVersion: Long, newVersion: Long) {
        val realmSchema = realm.schema
        var oldVersion = oldVersion

        // Migration についてはここ
        // https://qiita.com/kumas/items/c98f1006776ad141d5f7
    }
}