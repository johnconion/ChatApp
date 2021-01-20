package tokyo.crouton.component_chat.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import tokyo.crouton.base.AutoDisposable
import tokyo.crouton.base.AutoDisposableDelegation
import tokyo.crouton.base.usecase.events
import tokyo.crouton.base.usecase.execute
import tokyo.crouton.base.usecase.filterIsFailure
import tokyo.crouton.component_chat.R
import tokyo.crouton.component_chat.R.string
import tokyo.crouton.component_chat.usecase.PostMyTextUseCase
import tokyo.crouton.component_chat.usecase.RemovePostUseCase
import tokyo.crouton.component_chat.usecase.ResetAllChatUseCase
import tokyo.crouton.domain.chat.PostId
import tokyo.crouton.domain.store.ChatListItemsStore
import javax.inject.Inject


@AndroidEntryPoint
class ChatActivity : AppCompatActivity(), AutoDisposable by AutoDisposableDelegation(),
    ChatListMessagePostBinder.Actions {

    @Inject
    lateinit var chatListAdapter: ChatListAdapter

    @Inject
    lateinit var chatListItemsStore: ChatListItemsStore

    @Inject
    lateinit var postMyTextUseCase: PostMyTextUseCase

    @Inject
    lateinit var resetAllChatUseCase: ResetAllChatUseCase

    @Inject
    lateinit var removePostUseCase: RemovePostUseCase

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

        postButton.setOnLongClickListener {
            showResetChatListDialog()
            true
        }

        chatListItemsStore.updates()
            .subscribe {
                chatList.scrollToPosition(chatListItemsStore.size - 1)
            }.autoDispose()

        postMyTextUseCase.events()
            .filterIsFailure()
            .subscribe { makeText(this, string.error_toast, Toast.LENGTH_SHORT).show() }
            .autoDispose()
    }

    override fun onDestroy() {
        super.onDestroy()
        dispose()
    }

    companion object {
        fun createIntent(context: Context) = Intent(context, ChatActivity::class.java)
    }

    override fun removePost(postId: PostId) {
        AlertDialog.Builder(this)
            .setTitle(string.common_confirmation_dialog_title)
            .setMessage(string.remove_post_dialog_message)
            .setPositiveButton(string.dialog_ok_button) { _, _ ->
                removePostUseCase.execute(postId)
            }
            .setNegativeButton(string.dialog_cancel_string, null)
            .show()
    }

    private fun showResetChatListDialog() {
        AlertDialog.Builder(this)
            .setTitle(string.common_confirmation_dialog_title)
            .setMessage(string.reset_all_chat_dialog_message)
            .setPositiveButton(string.dialog_ok_button) { _, _ ->
                resetAllChatUseCase.execute()
            }
            .setNegativeButton(string.dialog_cancel_string, null)
            .show()
    }
}