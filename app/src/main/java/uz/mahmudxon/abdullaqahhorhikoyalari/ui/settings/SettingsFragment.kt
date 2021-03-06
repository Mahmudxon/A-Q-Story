package uz.mahmudxon.abdullaqahhorhikoyalari.ui.settings

import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.View
import android.widget.CompoundButton
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import androidx.core.widget.CompoundButtonCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_settings.*
import uz.mahmudxon.abdullaqahhorhikoyalari.R
import uz.mahmudxon.abdullaqahhorhikoyalari.core.lists.adapter.ThemeAdapter
import uz.mahmudxon.abdullaqahhorhikoyalari.core.util.setIconColor
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.BaseFragment
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.theme.Theme
import javax.inject.Inject

class SettingsFragment : BaseFragment(R.layout.fragment_settings), ThemeAdapter.INotifyThemeChange,
    CompoundButton.OnCheckedChangeListener {

    @Inject
    lateinit var themeAdapter: ThemeAdapter

    override fun onCreate(view: View) {
        font_seekbar?.setOnSeekBarChangeListener(mySeekBarChangeListener)
        back?.setOnClickListener { activity?.onBackPressed() }
        val str = intArrayOf(
            R.string.str1,
            R.string.str2,
            R.string.str3,
            R.string.str4,
            R.string.str5,
            R.string.str6,
            R.string.str7,
            R.string.str8,
            R.string.str9,
            R.string.str10
        )
        sample?.text = context?.getText(str.random())
        theme_list?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        theme_list?.adapter = themeAdapter
        themeAdapter.swapData(Theme.getAll())
        themeAdapter.setOnThemeChangeListener = this
        checkbox?.setOnCheckedChangeListener(this)
        // checkbox?.isChecked = prefs.get(prefs.useValumeKey, false)
        ll_use_value_key?.setOnClickListener { checkbox?.let { it.isChecked = !it.isChecked } }
    }


    override fun onCreateFont(fontSize: Float, fontFamily: Int?) {
        super.onCreateFont(fontSize, fontFamily)
        font_seekbar?.progress = fontSize.toInt() - 12
        text_size?.text = "${fontSize.toInt()}"
        sample?.textSize = fontSize
        sample_auth?.textSize = fontSize + 2
    }


    override fun onCreateTheme(theme: Theme) {
        super.onCreateTheme(theme)
        context?.let {
            val primaryColorDark = ContextCompat.getColor(it, theme.primaryColorDark)
            val primaryTextColor = ContextCompat.getColor(it, theme.primaryTextColor)
            val secondaryTextColor = ContextCompat.getColor(it, theme.secondaryTextColor)
            val primaryColor = ContextCompat.getColor(it, theme.primaryColor)

            action_bar?.setBackgroundColor(primaryColorDark)
            back?.setIconColor(secondaryTextColor)
            action_bar_title?.setTextColor(secondaryTextColor)

            text_font_size?.setTextColor(primaryTextColor)
            text_theme?.setTextColor(primaryTextColor)
            text_size?.setTextColor(primaryTextColor)
            sample?.setTextColor(primaryTextColor)
            sample_auth?.setTextColor(primaryTextColor)
            font_seekbar?.progressDrawable?.colorFilter =
                PorterDuffColorFilter(primaryTextColor, PorterDuff.Mode.MULTIPLY)
            font_seekbar?.thumb?.colorFilter =
                PorterDuffColorFilter(primaryTextColor, PorterDuff.Mode.SRC_IN)
            card_sample?.setCardBackgroundColor(primaryColor)
            use_value_key?.setTextColor(primaryTextColor)
            val states = arrayOf(
                intArrayOf(-android.R.attr.state_enabled),
                intArrayOf(android.R.attr.state_enabled),
                intArrayOf(-android.R.attr.state_checked),
                intArrayOf(android.R.attr.state_pressed)
            )

            val colors = intArrayOf(
                primaryTextColor,
                primaryTextColor,
                primaryTextColor,
                primaryTextColor
            )
            CompoundButtonCompat.setButtonTintList(checkbox, ColorStateList(states, colors))
            checkbox?.isChecked = prefs.get(prefs.useValumeKey, false)
        }
    }

    private val mySeekBarChangeListener = object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            if (fromUser) {
                val data = (progress + 12).toFloat()
                prefs.save(prefs.fontSize, data)
                notifyFontChanged()
            }
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {

        }

        override fun onStopTrackingTouch(seekBar: SeekBar?) {

        }
    }

    override fun onChangeTheme() {
        notifyThemeChanged()
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        prefs.save(prefs.useValumeKey, isChecked)
    }
}

