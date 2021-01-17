package tokyo.crouton.network.api

import io.reactivex.Single
import retrofit2.http.POST
import tokyo.crouton.network.model.ChatBotResponse

interface ChatApi {
    @POST("macros/s/AKfycbzDM4WOlnRCI-2R0wkS6A6ODiXsn_NAUFZ5s6oOToPxoP9X2K0/exec")
    fun postMessage(): Single<ChatBotResponse>
}