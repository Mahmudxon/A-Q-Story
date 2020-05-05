package uz.mahmudxon.abdullaqahhorhikoyalari.core.dialog

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.dialog_settings.*
import kotlinx.android.synthetic.main.dialog_settings.view.*
import uz.mahmudxon.abdullaqahhorhikoyalari.R
import uz.mahmudxon.abdullaqahhorhikoyalari.core.lists.adapter.ThemeAdapter
import uz.mahmudxon.abdullaqahhorhikoyalari.core.util.Prefs
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.theme.Theme

class SettingsDialog(context: Context) : AlertDialog(context), ThemeAdapter.INotifyThemeChange {

    var listener: ISettingsChangeListener? = null

    private var themeAdapter: ThemeAdapter = ThemeAdapter()

    private var fontSize: Float = 0F


    private var prefs: Prefs


    init {
        val view = layoutInflater.inflate(R.layout.dialog_settings, null, false)
        view.apply {
            theme_list?.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            theme_list?.adapter = themeAdapter

        }
        prefs = Prefs(context)
        themeAdapter.prefs = prefs
        themeAdapter.setOnThemeChangeListener = this
        setView(view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setThemes()
        fontSize = prefs.get(prefs.fontSize, 16F)
        plus?.isEnabled = fontSize != 30F
        minus?.isEnabled = fontSize != 12F
        font_size?.text = "${fontSize.toInt()}"
        plus?.setOnClickListener {
            fontSize++
            fonSizeChange()
        }
        minus?.setOnClickListener {
            fontSize--
            fonSizeChange()
        }

        onChangeTheme()
    }


    private fun fonSizeChange() {
        plus?.isEnabled = fontSize != 30F
        minus?.isEnabled = fontSize != 12F
        font_size?.text = "${fontSize.toInt()}"
        prefs.save(prefs.fontSize, fontSize)
        listener?.onFontChange()
    }

    private fun setThemes() {
        val data = ArrayList<Theme>()
        val id = prefs.get(prefs.stockTheme, Theme.THEME_CLASSIC)
        val theme =
            if (id != Theme.READ_MODE) Theme.getThemeById(id) else Theme.getThemeById(Theme.THEME_CLASSIC)
        data.add(theme)
        data.add(Theme.getThemeById(Theme.THEME_NIGHT))
        data.add(Theme.getThemeById(Theme.READ_MODE))
        themeAdapter.swapData(data)
    }

    override fun onChangeTheme() {
        val theme = Theme.getThemeById(prefs.get(prefs.theme, Theme.THEME_CLASSIC))
        val primaryColor = ContextCompat.getColor(context, theme.primaryColor)
        val primaryTextColor = ContextCompat.getColor(context, theme.primaryTextColor)

        container?.setBackgroundColor(primaryColor)
        text_font_size?.setTextColor(primaryTextColor)
        font_size?.setTextColor(primaryTextColor)
        minus?.setTextColor(primaryTextColor)
        plus?.setTextColor(primaryTextColor)
        text_theme?.setTextColor(primaryTextColor)
        listener?.onThemeChange()
    }

    interface ISettingsChangeListener {
        fun onThemeChange()
        fun onFontChange()
    }
}