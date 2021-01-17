package tokyo.crouton.repository_chat.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import tokyo.crouton.domain.store.ChatListItemsStore
import tokyo.crouton.repository_chat.ChatListItemsStoreImpl
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class ApplicationModule {
    @Singleton
    @Binds
    abstract fun provideChatListItemStore(impl: ChatListItemsStoreImpl): ChatListItemsStore
}
