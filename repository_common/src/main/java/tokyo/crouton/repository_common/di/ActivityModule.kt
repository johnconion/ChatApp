package tokyo.crouton.repository_common.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import tokyo.crouton.domain.store.ChatListItemsStore
import tokyo.crouton.repository_common.ChatListItemsStoreImpl

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {
    @Provides
    fun provideChatListItemStore(impl: ChatListItemsStoreImpl): ChatListItemsStore = impl
}