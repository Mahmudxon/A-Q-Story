package uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.fragments.feefback

interface IFeedback {
    interface IView {
        fun showLoading()
        fun hideLoading()
        fun onSuccess()
        fun onError()
    }

    interface IPresenter {
        fun sendFeedback(username: String, email: String, message: String, appname: String)
    }
}