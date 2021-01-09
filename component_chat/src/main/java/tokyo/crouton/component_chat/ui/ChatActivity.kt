package tokyo.crouton.component_chat.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import io.realm.Realm
import io.realm.kotlin.where
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

        realm.where<RealmChat>()
            .findAllAsync()
            .addChangeListener { chats ->
                Log.d("WASSA", "AA!! : " + chats.toString())
            }

//        val button = findViewById<TextView>(R.id.button)
//        button.setOnClickListener {
//            val chat = RealmChat().apply {
//                text = "aaaaa"
//            }
//
//            realm.executeTransaction {
//                it.insert(chat)
//            }
//
//            Log.d("WASSA", realm.where<RealmChat>().findAll().toString())
//        }
    }

    companion object {
        fun createIntent(context: Context) = Intent(context, ChatActivity::class.java)
    }
}