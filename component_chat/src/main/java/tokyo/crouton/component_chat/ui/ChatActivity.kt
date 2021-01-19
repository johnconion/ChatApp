package tokyo.crouton.component_chat.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import tokyo.crouton.base.AutoDisposable
import tokyo.crouton.base.AutoDisposableDelegation
import tokyo.crouton.base.usecase.UseCaseDispatcher
import tokyo.crouton.base.usecase.UseCaseEvent.Failure
import tokyo.crouton.base.usecase.UseCaseEvent.Success
import tokyo.crouton.base.usecase.execute
import tokyo.crouton.component_chat.R
import tokyo.crouton.component_chat.R.string
import tokyo.crouton.component_chat.usecase.PostMyTextUseCase
import tokyo.crouton.component_chat.usecase.RemoveAllPostsUseCase
import tokyo.crouton.domain.store.ChatListItemsStore
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
    lateinit var removeAllPostsUseCase: RemoveAllPostsUseCase

    private val chatList: RecyclerView by lazy { findViewById<RecyclerView>(R.id.chat_list) }
    private val postEditText: EditText by lazy { findViewById<EditText>(R.id.post_text) }
    private val postButton: TextView by lazy { findViewById<TextView>(R.id.post_button) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        addChild(chatListAdapter)
        chatList.adapter = chatListAdapter

        postButton.setOnClickListener {
            postMyTextUseCase.execute(postEditText.text.toString())
            postEditText.text.clear()
        }

        //
        postButton.setOnLongClickListener {
            removeAllPostsUseCase.execute()
            true
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
                    }
                    is Failure<*> -> {
                        Log.d("WASSA", "Failure")
                        makeText(this, string.error_toast, Toast.LENGTH_SHORT).show()
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