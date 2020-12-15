package tokyo.crouton.chatapp.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.realm.Realm
import io.realm.RealmConfiguration
import tokyo.crouton.datasource_realm.Migration

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()

        val latestSchemaVersion = 0L

        // Realm 初期化
        Realm.init(this)
        val builder = RealmConfiguration.Builder()
        builder.schemaVersion(latestSchemaVersion).migration(Migration())
        val config = builder.build()
        Realm.setDefaultConfiguration(config)
    }
}