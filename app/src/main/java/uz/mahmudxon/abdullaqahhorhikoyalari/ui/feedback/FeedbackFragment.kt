package uz.mahmudxon.abdullaqahhorhikoyalari.ui.feedback

import android.view.View
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.fragment_feedback.*
import uz.mahmudxon.abdullaqahhorhikoyalari.R
import uz.mahmudxon.abdullaqahhorhikoyalari.core.dialog.LoadingDialog
import uz.mahmudxon.abdullaqahhorhikoyalari.core.util.setIconColor
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.BaseFagment
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.fragments.feedback.IFeedback
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.theme.Theme
import javax.inject.Inject

class FeedbackFragment : BaseFagment(R.layout.fragment_feedback), IFeedback.IView {

    @Inject
    lateinit var presenter: IFeedback.IPresenter

    var dialog: LoadingDialog? = null

    override fun onCreate(view: View) {
        back?.setOnClickListener {
            hideKeyBoard()
            activity?.onBackPressed()
        }
        send?.setOnClickListener {
            val username = username?.text.toString()
            val email = email?.text.toString()
            val message = message?.text.toString()
            if (username.trim().isEmpty()) {
                toast("Iltimos, Ismingizni kiriting!")
                return@setOnClickListener
            }

            if (email.trim().isEmpty()) {
                toast("Siz bilan bog'lanish mumkin bo'lgan holatlar uchun e-mail yoki telefon raqam yozishingizni so'raymiz!")
                return@setOnClickListener
            }

            if (message.trim().isEmpty()) {
                toast("Xabar yozmagansiz!")
                return@setOnClickListener
            }
            hideKeyBoard()
            presenter.sendFeedback(
                username,
                email,
                message,
                context?.getString(R.string.app_name) ?: "Mahmudxon"
            )
        }
    }

    override fun onCreateTheme(theme: Theme) {
        super.onCreateTheme(theme)
        context?.let {
            val primaryColorDark = ContextCompat.getColor(it, theme.primaryColorDark)
            val primaryTextColor = ContextCompat.getColor(it, R.color.colorBlack)
            val secondaryTextColor = ContextCompat.getColor(it, theme.secondaryTextColor)


            action_bar?.setBackgroundColor(primaryColorDark)
            back?.setIconColor(secondaryTextColor)
            send?.setIconColor(secondaryTextColor)
            action_bar_title?.setTextColor(secondaryTextColor)

            username?.setTextColor(primaryTextColor)
            email?.setTextColor(primaryTextColor)
            message?.setTextColor(primaryTextColor)

        }
    }

    override fun showLoading() {
        dialog = LoadingDialog(requireContext(), "Xabaringiz jo'natilmoqda...")
        dialog?.show()
    }

    override fun hideLoading() {
        dialog?.dismiss()
    }

    override fun onSuccess() {
        toast("Xabaringiz jo'natildi, albatta uni ko'rib chiqamiz! E'tibor uchun tashakkur!")
        activity?.onBackPressed()
    }

    override fun onError() {
        toast("Xabar jo'natilmadi! Ehtimol, internetga ulanish imkoni yo'qdir :-(")
    }
}
