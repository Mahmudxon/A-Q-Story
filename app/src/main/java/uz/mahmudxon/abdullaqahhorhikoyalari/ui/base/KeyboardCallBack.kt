package uz.mahmudxon.abdullaqahhorhikoyalari.ui.base

import android.view.KeyEvent

interface KeyboardCallBack {
    fun dispatchKeyEvent(event: KeyEvent?, default: Boolean): Boolean
}