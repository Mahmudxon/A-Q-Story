package uz.mahmudxon.abdullaqahhorhikoyalari.ui.webview

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.fragment_webview.*
import uz.mahmudxon.abdullaqahhorhikoyalari.R
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.BaseFragment


class WebviewFragment : BaseFragment(R.layout.fragment_webview) {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(view: View) {
        back?.setOnClickListener { activity?.onBackPressed() }
        webView?.settings?.javaScriptEnabled = true
        webView?.settings?.allowContentAccess = true
        webView?.webChromeClient = MyChromeClient()
        webView?.webViewClient = MyWebViewClient()
        webView?.loadUrl("https://t.me/s/mahmudxon_uz")
    }

    inner class MyChromeClient : WebChromeClient() {
        override fun getDefaultVideoPoster(): Bitmap? {
            return if (super.getDefaultVideoPoster() == null) {
                BitmapFactory.decodeResource(
                    requireContext().resources,
                    R.drawable.tg_channel
                )
            } else {
                super.getDefaultVideoPoster()
            }
        }
    }

    inner class MyWebViewClient : WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            webView?.scrollTo(0, 1000000000)
        }
    }
}