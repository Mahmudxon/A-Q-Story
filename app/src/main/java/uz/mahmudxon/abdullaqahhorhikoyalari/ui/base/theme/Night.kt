package uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.theme

import uz.mahmudxon.abdullaqahhorhikoyalari.R

class Night : Theme() {
    override val id: Long
        get() = THEME_NIGHT
    override val primaryColor: Int
        get() = R.color.Drakula
    override val primaryColorDark: Int
        get() = R.color.DrakulaDark
    override val primaryTextColor: Int
        get() = R.color.DrakulaText
    override val secondaryTextColor: Int
        get() = R.color.DrakulaText
    override val statusBarColor: Int
        get() = R.color.DrakulaDark
    override val assetsColor: Int
        get() = R.color.colorPrimaryDark
}