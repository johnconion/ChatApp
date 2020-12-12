package tokyo.crouton.chatapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import tokyo.crouton.base.ActivityIntentResolver
import tokyo.crouton.chatapp.R
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var activityIntentResolver: ActivityIntentResolver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startActivity(activityIntentResolver.chat.getChatActivityIntent())
    }
}