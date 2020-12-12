package tokyo.crouton.component_chat.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tokyo.crouton.component_chat.R

//@AndroidEntryPoint
class ChatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
    }

    companion object {
        fun createIntent(context: Context) = Intent(context, ChatActivity::class.java)
    }
}