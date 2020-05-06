package uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.theme

import uz.mahmudxon.abdullaqahhorhikoyalari.R

class ReadMode : Theme() {
    override val id: Long
        get() = Theme.READ_MODE
    override val primaryColor: Int
        get() = R.color.colorReadModeLight
    override val primaryColorDark: Int
        get() = R.color.colorReadModeDark
    override val primaryTextColor: Int
        get() = R.color.colorBlack
    override val secondaryTextColor: Int
        get() = R.color.colorWhite
    override val statusBarColor: Int
        get() = R.color.colorReadModeDark
    override val assetsColor: Int
        get() = R.color.colorBlack
}