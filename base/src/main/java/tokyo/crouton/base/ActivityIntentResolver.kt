package tokyo.crouton.base

import android.content.Intent

interface ActivityIntentResolver {

    val chat: Chat

    interface Chat {
        fun getChatActivityIntent(): Intent
    }
}