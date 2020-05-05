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
        val READ_MODE = 2L
        val THEME_GREEN = 3L
        val RED = 4L
        val PINK = 5L
        val ORANGE = 6L
        val LIGHT = 7L

        fun getAll(): ArrayList<Theme> {
            val result = ArrayList<Theme>()
            result.add(Classic())
            result.add(Night())
            result.add(ReadMode())
            result.add(LightTheme())
            result.add(GreenTheme())
            result.add(RedTheme())
            result.add(PinkTheme())
            result.add(OrangeTheme())
            return result
        }

        fun getThemeById(id: Long): Theme {
            getAll().forEach {
                if (it.id == id)
                    return it
            }

            return Classic()
        }

    }
}