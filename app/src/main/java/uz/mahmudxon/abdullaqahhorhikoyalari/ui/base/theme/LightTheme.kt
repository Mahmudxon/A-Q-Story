package uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.theme

import uz.mahmudxon.abdullaqahhorhikoyalari.R

class LightTheme : Theme() {
    override val id: Long
        get() = LIGHT
    override val primaryColor: Int
        get() = R.color.colorPrimary
    override val primaryColorDark: Int
        get() = R.color.colorWhite
    override val primaryTextColor: Int
        get() = R.color.colorBlack
    override val secondaryTextColor: Int
        get() = R.color.colorBlack
    override val statusBarColor: Int
        get() = R.color.colorBlack
    override val assetsColor: Int
        get() = R.color.colorBlack
}