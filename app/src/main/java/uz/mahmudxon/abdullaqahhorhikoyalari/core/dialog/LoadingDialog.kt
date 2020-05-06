package uz.mahmudxon.abdullaqahhorhikoyalari.core.dialog

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.dialog_loading.view.*
import uz.mahmudxon.abdullaqahhorhikoyalari.R
import uz.mahmudxon.abdullaqahhorhikoyalari.core.util.Prefs
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.theme.Theme

class LoadingDialog(context: Context, message: String) : AlertDialog(context) {
    private val prefs = Prefs(context)

    init {
        this.setCancelable(false)
        val theme = Theme.getThemeById(prefs.get(prefs.theme, Theme.THEME_CLASSIC))
        val backColor = ContextCompat.getColor(context, theme.primaryColor)
        val assetsColor = ContextCompat.getColor(context, theme.assetsColor)
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null, false)
        view.apply {
            setBackgroundColor(backColor)
            progress_bar?.indeterminateDrawable?.colorFilter =
                PorterDuffColorFilter(assetsColor, PorterDuff.Mode.SRC_IN)
            text?.text = message
            text?.setTextColor(assetsColor)
        }
        setView(view)
    }
}