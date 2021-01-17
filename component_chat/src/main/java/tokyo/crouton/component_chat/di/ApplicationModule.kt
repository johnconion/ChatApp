package tokyo.crouton.component_chat.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.realm.Realm

@Module
@InstallIn(ApplicationComponent::class)
class ApplicationModule() {
    @Provides
    fun provideRealmInstance(): Realm = Realm.getDefaultInstance()
}