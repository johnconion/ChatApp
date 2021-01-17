package tokyo.crouton.repository_chat.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import tokyo.crouton.domain.repository.ChatRepository
import tokyo.crouton.repository_chat.repository.ChatRepositoryImpl

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {
    @Provides
    fun provideChatRepository(impl: ChatRepositoryImpl): ChatRepository = impl
}
