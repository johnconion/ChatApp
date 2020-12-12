package tokyo.crouton.chatapp

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import tokyo.crouton.base.ActivityIntentResolver
import tokyo.crouton.component_chat.di.ChatActivityIntentResolverImpl
import javax.inject.Inject

class ActivityIntentResolverImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ActivityIntentResolver {
    override val chat: ActivityIntentResolver.Chat = ChatActivityIntentResolverImpl(context)
}