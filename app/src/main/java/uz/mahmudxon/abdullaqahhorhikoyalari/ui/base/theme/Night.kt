package uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.theme

import uz.mahmudxon.abdullaqahhorhikoyalari.R
import javax.inject.Inject

class Night @Inject constructor() : Theme() {
    override val id: Long
        get() = THEME_NIGHT
    override val primaryColor: Int
        get() = R.color.colorBlackLight
    override val primaryColorDark: Int
        get() = R.color.colorBlack
    override val primaryTextColor: Int
        get() = R.color.colorWhite
    override val secondaryTextColor: Int
        get() = R.color.colorWhite
    override val statusBarColor: Int
        get() = R.color.colorBlack
}