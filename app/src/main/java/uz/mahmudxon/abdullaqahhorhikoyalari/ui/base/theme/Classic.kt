package uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.theme

import uz.mahmudxon.abdullaqahhorhikoyalari.R

class Classic : Theme() {
    override val id: Long
        get() = THEME_CLASSIC
    override val primaryColor: Int
        get() = R.color.colorPrimary
    override val primaryColorDark: Int
        get() = R.color.colorPrimaryDark
    override val primaryTextColor: Int
        get() = R.color.colorBlack
    override val secondaryTextColor: Int
        get() = R.color.colorWhite
    override val statusBarColor: Int
        get() = R.color.colorPrimaryDark
    override val assetsColor: Int
        get() = R.color.colorPrimaryDark
}