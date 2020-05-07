package uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.fragments.feefback

import com.creativityapps.gmailbackgroundlibrary.BackgroundMail
import javax.inject.Inject

class FeedbackPresenterImpl @Inject constructor(val view: IFeedback.IView) : IFeedback.IPresenter,
    BackgroundMail.OnSendingCallback {

    @Inject
    lateinit var emailSender: BackgroundMail.Builder

    override fun sendFeedback(username: String, email: String, message: String, appname: String) {
        view.showLoading()
        emailSender.withMailTo("umarxonovmahmudxon@gmail.com")
            .withType(BackgroundMail.TYPE_PLAIN)
            .withSubject("feedback from (android app) - $appname")
            .withSenderName(username)
            .withBody(message)
            .withOnSuccessCallback(this)
            .send()
    }

    override fun onSuccess() {
        view.hideLoading()
        view.onSuccess()
    }

    override fun onFail(p0: Exception?) {
        view.hideLoading()
        view.onError()
    }
}