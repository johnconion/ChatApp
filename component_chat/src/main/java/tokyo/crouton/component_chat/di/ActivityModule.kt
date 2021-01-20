package tokyo.crouton.component_chat.di

import android.app.Activity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import tokyo.crouton.component_chat.ui.ChatListMessagePostBinder.Actions

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {
    @Provides
    fun provideChatListActions(activity: Activity): Actions = activity as Actions
}