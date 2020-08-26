package uz.mahmudxon.abdullaqahhorhikoyalari.ui.webview

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.webkit.URLUtil
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.fragment_webview.*
import uz.mahmudxon.abdullaqahhorhikoyalari.R
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.BaseFragment


class WebviewFragment : BaseFragment(R.layout.fragment_webview), View.OnKeyListener {

    private val telegramUrl = "https://t.me/s/mahmudxon_uz"
    private val joinChatUrl = "https://t.me/joinchat/AAAAAE2VrUfRXVPmv1aygw"

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(view: View) {
        back?.setOnClickListener { activity?.onBackPressed() }
        view.isFocusableInTouchMode = true
        view.requestFocus()
        view.setOnKeyListener(this)
        webView?.settings?.javaScriptEnabled = true
        webView?.settings?.allowContentAccess = true
        webView?.settings?.loadWithOverviewMode = true
        webView?.webChromeClient = MyChromeClient()
        webView?.webViewClient = MyWebViewClient()
        webView?.loadUrl(telegramUrl)
    }

    inner class MyChromeClient : WebChromeClient() {
        override fun getDefaultVideoPoster(): Bitmap? {
            return if (super.getDefaultVideoPoster() == null) {
                BitmapFactory.decodeResource(
                    requireContext().resources,
                    R.drawable.telegram
                )
            } else {
                super.getDefaultVideoPoster()
            }
        }
    }

    inner class MyWebViewClient : WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            pb?.visibility = View.GONE
            webView?.visibility = View.VISIBLE
        }

        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            Log.d("TTT", "shouldOverrideUrlLoading: $url")
            return if (appInstalledOrNot(url!!) || !URLUtil.isNetworkUrl(url)) {
                openUrlIntent(url)
                true
            } else {
                if (!url.startsWith(telegramUrl, true)) {
                    webView?.stopLoading()
                    if (url.startsWith("https://telegram.org/dl?tme=", true))
                        openUrlIntent(joinChatUrl)
                    else
                        openUrlIntent(url)
                    true
                } else false
            }
        }
    }

    private fun openUrlIntent(url: String) =
        activity?.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))

    override fun onDestroy() {
        webView?.destroy()
        super.onDestroy()
    }

    private fun appInstalledOrNot(uri: String): Boolean {
        return try {
            val pm: PackageManager = requireActivity().packageManager
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }

    override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event?.action == KeyEvent.ACTION_DOWN) {
            Log.d("TTT", "onKey: Back")
            return if (webView?.canGoBack() == true) {
                webView?.goBack()
                true
            } else false
        }
        return false
    }
}