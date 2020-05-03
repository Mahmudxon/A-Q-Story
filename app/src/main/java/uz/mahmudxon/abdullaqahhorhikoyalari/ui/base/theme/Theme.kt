package uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.theme

abstract class Theme {
    abstract val id: Long
    abstract val primaryColor: Int
    abstract val primaryColorDark: Int
    abstract val primaryTextColor: Int
    abstract val secondaryTextColor: Int
    abstract val statusBarColor: Int

    companion object {
        val THEME_CLASSIC = 0L
        val THEME_NIGHT = 1L
        val THEME_BLUELIGHT = 2L
    }
}