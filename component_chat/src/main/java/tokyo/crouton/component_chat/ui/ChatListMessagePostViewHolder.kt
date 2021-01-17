package tokyo.crouton.component_chat.ui

import android.view.View
import android.widget.TextView

interface ChatListMessagePostViewHolder {
    val backgroundView: View
    val messageText: TextView
    val timeText: TextView
}