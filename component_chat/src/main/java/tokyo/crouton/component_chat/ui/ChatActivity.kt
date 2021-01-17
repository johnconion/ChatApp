package tokyo.crouton.component_chat.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import tokyo.crouton.base.AutoDisposable
import tokyo.crouton.base.AutoDisposableDelegation
import tokyo.crouton.base.UseCaseDispatcher
import tokyo.crouton.base.execute
import tokyo.crouton.base.usecase.UseCaseEvent.Failure
import tokyo.crouton.base.usecase.UseCaseEvent.Success
import tokyo.crouton.component_chat.R
import tokyo.crouton.component_chat.usecase.PostMyTextUseCase
import tokyo.crouton.domain.store.ChatListItemsStore
import tokyo.crouton.network.APIClient
import javax.inject.Inject

@AndroidEntryPoint
class ChatActivity : AppCompatActivity(), AutoDisposable by AutoDisposableDelegation() {

    @Inject
    lateinit var chatListAdapter: ChatListAdapter

    @Inject
    lateinit var chatListItemsStore: ChatListItemsStore

    @Inject
    lateinit var postMyTextUseCase: PostMyTextUseCase

    @Inject
    lateinit var useCaseDispatcher: UseCaseDispatcher

    @Inject
    lateinit var APIClient: APIClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        addChild(chatListAdapter)
        val chatList = findViewById<RecyclerView>(R.id.chat_list)
        chatList.adapter = chatListAdapter

        val postEditText = findViewById<EditText>(R.id.post_text)
        val postButton = findViewById<TextView>(R.id.post_button)
        postButton.setOnClickListener {
            postMyTextUseCase.execute(postEditText.text.toString())
        }

        chatListItemsStore.updates()
            .subscribe {
                chatList.scrollToPosition(chatListItemsStore.size - 1)
            }.autoDispose()

        // これは酷いのでなんとかする
        useCaseDispatcher.events()
            .filter {
                when (it) {
                    is Success<*> -> it.type == PostMyTextUseCase::class.java
                    is Failure<*> -> it.type == PostMyTextUseCase::class.java
                }
            }.subscribe {
                when (it) {
                    is Success<*> -> {
                        Log.d("WASSA", "Success")
                        postEditText.text.clear()
                        chatListAdapter.notifyDataSetChanged()
                    }
                    is Failure<*> -> {
                        Log.d("WASSA", "Failure")
                    }
                }
            }.autoDispose()
    }

    override fun onDestroy() {
        super.onDestroy()
        dispose()
    }

    companion object {
        fun createIntent(context: Context) = Intent(context, ChatActivity::class.java)
    }
}