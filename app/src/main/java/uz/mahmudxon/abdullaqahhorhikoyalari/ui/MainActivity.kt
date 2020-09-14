package uz.mahmudxon.abdullaqahhorhikoyalari.ui

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import android.os.Build
import android.view.KeyEvent
import android.view.View
import android.view.ViewAnimationUtils
import androidx.core.view.GravityCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import uz.mahmudxon.abdullaqahhorhikoyalari.R
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.BaseActivity
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.KeyboardCallBack
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.theme.IAnimationThemeChanger
import kotlin.math.hypot

class MainActivity : BaseActivity(R.layout.activity_main), IAnimationThemeChanger {

    private var finalRadius: Float = 0F

    var keyboardCallBack: KeyboardCallBack? = null

    override fun onAfterCreate() {
    }

    override fun onBackPressed() {
        if (drawer_layout?.isDrawerOpen(GravityCompat.START) == true) {
            drawer_layout?.closeDrawer(GravityCompat.START)
            return
        }


        if (search_view?.isIconified == false) {
            search_view?.setQuery("", false)
            search_view?.isIconified = true
            return
        }

        super.onBackPressed()
    }

    override fun takeScreenshot() {
        val container = window.decorView
        val w = container.measuredWidth
        val h = container.measuredHeight
        finalRadius = hypot(w.toFloat(), h.toFloat())
        val bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        container.draw(canvas)
        imageView?.setImageBitmap(bitmap)
        imageView?.visibility = View.VISIBLE
    }

    override fun startAnimation(x: Int, y: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val anim = ViewAnimationUtils.createCircularReveal(
                window.decorView,
                x,
                y + getStatusBarHeight(),
                0F,
                finalRadius
            )
            anim.duration = 600L
            anim.start()
        }
    }

    override fun dispatchKeyEvent(event: KeyEvent?): Boolean {

        keyboardCallBack?.let {
            return it.dispatchKeyEvent(event, super.dispatchKeyEvent(event))
        }

        return super.dispatchKeyEvent(event)
    }

    fun getStatusBarHeight(): Int {
        val rectgle = Rect()
        window.decorView.getWindowVisibleDisplayFrame(rectgle)
        return rectgle.top
    }

}

