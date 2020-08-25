package uz.mahmudxon.abdullaqahhorhikoyalari.ui.about_app

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_about_app.*
import uz.mahmudxon.abdullaqahhorhikoyalari.BuildConfig
import uz.mahmudxon.abdullaqahhorhikoyalari.R
import uz.mahmudxon.abdullaqahhorhikoyalari.core.util.setIconColor
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.BaseFragment
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.theme.Theme


class AboutAppFragment : BaseFragment(R.layout.fragment_about_app), View.OnClickListener {

    override fun onCreate(view: View) {
        val version: String = "v${BuildConfig.VERSION_NAME}"
        app_version.text = version
        back?.setOnClickListener(this)
        telegram?.setOnClickListener(this)
        facebook?.setOnClickListener(this)
        gmail?.setOnClickListener(this)
        play_store?.setOnClickListener(this)
    }

    override fun onCreateTheme(theme: Theme) {
        super.onCreateTheme(theme)
        context?.let {
            val primaryDarkColor = ContextCompat.getColor(it, theme.primaryColorDark)
            val secondaryText = ContextCompat.getColor(it, theme.secondaryTextColor)
            val primaryText = ContextCompat.getColor(it, theme.primaryTextColor)
            val assestsColor = ContextCompat.getColor(it, theme.assetsColor)

            action_bar?.setBackgroundColor(primaryDarkColor)
            back?.setIconColor(secondaryText)
            action_bar_title?.setTextColor(secondaryText)
            app_version?.setTextColor(primaryText)
            copyright?.setTextColor(primaryText)
            app_name?.setTextColor(assestsColor)
            app_description?.setTextColor(primaryText)
            developer?.setTextColor(assestsColor)
            tvGmail?.setTextColor(primaryText)
            tvFacebook?.setTextColor(primaryText)
            tvTelegram?.setTextColor(primaryText)
            tvPlayStore?.setTextColor(primaryText)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {

            R.id.back -> {
                activity?.onBackPressed()
            }

            R.id.facebook -> {
                openUrlIntent("https://fb.com/mahmudxon_uz")
            }
            R.id.gmail -> {
                openUrlIntent("mailto:umarxonovmahmudxon@gamil.com")
            }
            R.id.play_store -> {
                openUrlIntent("https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}")
            }

            R.id.telegram -> {
                if (isNetworkAvailable())
                    findNavController().navigate(R.id.action_aboutAppFragment_to_webviewFragment)
                else openUrlIntent("https://t.me/joinchat/AAAAAE2VrUfRXVPmv1aygw")
            }

        }
    }

    private fun openUrlIntent(url: String) =
        activity?.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))

    private fun isNetworkAvailable(): Boolean {
        val manager =
            requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val networkInfo = manager!!.activeNetworkInfo
        var isAvailable = false
        if (networkInfo != null && networkInfo.isConnected) {
            // Network is present and connected
            isAvailable = true
        }
        return isAvailable
    }
}

