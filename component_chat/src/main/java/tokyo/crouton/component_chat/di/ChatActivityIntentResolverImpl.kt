package tokyo.crouton.component_chat.di

import android.content.Context
import android.content.Intent
import tokyo.crouton.base.ActivityIntentResolver
import tokyo.crouton.component_chat.ui.ChatActivity

class ChatActivityIntentResolverImpl(
    private val context: Context
) : ActivityIntentResolver.Chat {
    override fun getChatActivityIntent(): Intent =
        ChatActivity.createIntent(context)
}