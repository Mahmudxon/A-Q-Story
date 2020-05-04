package uz.mahmudxon.abdullaqahhorhikoyalari.ui.about_author

import android.view.View
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.fragment_about_author.*
import uz.mahmudxon.abdullaqahhorhikoyalari.R
import uz.mahmudxon.abdullaqahhorhikoyalari.core.util.setIconColor
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.BaseFagment
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.theme.Theme


class AboutAuthorFragment : BaseFagment(R.layout.fragment_about_author) {

    override fun onCreate(view: View) {
        back?.setOnClickListener { activity?.onBackPressed() }
    }

    override fun onCreateTheme(theme: Theme) {
        super.onCreateTheme(theme)
        context?.let {
            val primaryTextColor = ContextCompat.getColor(it, theme.primaryTextColor)
            val primaryColorDark = ContextCompat.getColor(it, theme.primaryColorDark)
            val secondaryTextColor = ContextCompat.getColor(it, theme.secondaryTextColor)
            title?.setTextColor(primaryTextColor)
            additional?.setTextColor(primaryTextColor)
            story?.setTextColor(primaryTextColor)
            action_bar_layout?.setBackgroundColor(primaryColorDark)
            back?.setIconColor(secondaryTextColor)
            action_bar_title?.setTextColor(secondaryTextColor)
        }
    }

    override fun onCreateFont(fontSize: Float, fontFamily: Int?) {
        super.onCreateFont(fontSize, fontFamily)
        title?.textSize = fontSize + 2
        additional?.textSize = fontSize
        story?.textSize = fontSize
    }
}
