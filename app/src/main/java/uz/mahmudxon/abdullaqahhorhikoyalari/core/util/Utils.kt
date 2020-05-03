package uz.mahmudxon.abdullaqahhorhikoyalari.core.util

import android.util.Log
import android.widget.ImageView
import androidx.appcompat.widget.SearchView

fun log(message: String) = Log.i("TTT", message)

fun ImageView.setIconColor(color: Int) {
    this.setColorFilter(color, android.graphics.PorterDuff.Mode.SRC_IN)
}

fun SearchView.getIconImageView(): ImageView? =
    findViewById<ImageView>(androidx.appcompat.R.id.search_button)

fun SearchView.getCancelIconImageView(): ImageView? =
    findViewById<ImageView>(androidx.appcompat.R.id.search_close_btn)

fun SearchView.getAutoComplete(): androidx.appcompat.widget.SearchView.SearchAutoComplete? =
    findViewById(androidx.appcompat.R.id.search_src_text)