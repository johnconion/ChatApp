package tokyo.crouton.component_chat.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import tokyo.crouton.component_chat.R
import javax.inject.Inject

@AndroidEntryPoint
class ChatActivity : AppCompatActivity() {

    @Inject
    lateinit var chatListAdapter: ChatListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        findViewById<RecyclerView>(R.id.chat_list).adapter = chatListAdapter
    }

    companion object {
        fun createIntent(context: Context) = Intent(context, ChatActivity::class.java)
    }
}