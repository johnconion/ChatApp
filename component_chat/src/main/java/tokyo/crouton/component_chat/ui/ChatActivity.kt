package tokyo.crouton.component_chat.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import io.realm.Realm
import tokyo.crouton.component_chat.R
import tokyo.crouton.datasource_realm.RealmChat
import tokyo.crouton.domain.store.ChatListItemsStore
import javax.inject.Inject

@AndroidEntryPoint
class ChatActivity : AppCompatActivity() {

    @Inject
    lateinit var chatListAdapter: ChatListAdapter

    @Inject
    lateinit var chatListItemsStore: ChatListItemsStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        findViewById<RecyclerView>(R.id.chat_list).adapter = chatListAdapter

        val realm = Realm.getDefaultInstance()

        val button = findViewById<TextView>(R.id.button)
        button.setOnClickListener {
            val chat = RealmChat().apply {
                text = "aaaa"
            }

            realm.executeTransaction {
                it.insert(chat)
            }
        }
    }

    companion object {
        fun createIntent(context: Context) = Intent(context, ChatActivity::class.java)
    }
}