package uz.mahmudxon.abdullaqahhorhikoyalari.ui.base

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import dagger.android.support.DaggerFragment
import uz.mahmudxon.abdullaqahhorhikoyalari.core.util.Prefs
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.theme.Classic
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.theme.Night
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.theme.Theme
import javax.inject.Inject

abstract class BaseFagment(@LayoutRes val layoutId: Int) : DaggerFragment() {

    lateinit var navController: NavController

    @Inject
    lateinit var nightTheme: Night

    @Inject
    lateinit var classicTheme: Classic

    @Inject
    lateinit var prefs: Prefs

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        onCreate(view)
        notifyThemeChanged()
        notifyFontChanged()
    }

    protected fun notifyThemeChanged() {
        when (prefs.get(prefs.theme, Theme.THEME_CLASSIC)) {
            Theme.THEME_NIGHT -> {
                onCreateTheme(nightTheme)
            }
            Theme.THEME_CLASSIC -> {
                onCreateTheme(classicTheme)
            }
        }
    }

    open fun onCreateTheme(theme: Theme) {
        context?.let {
            view?.setBackgroundColor(ContextCompat.getColor(it, theme.primaryColor))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                activity?.window?.statusBarColor =
                    ContextCompat.getColor(it, theme.primaryColorDark)
                activity?.window?.navigationBarColor =
                    ContextCompat.getColor(it, theme.statusBarColor)

            }

        }
    }

    fun toast(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    fun toast(@StringRes message: Int) {
        toast(context?.getString(message))
    }

    abstract fun onCreate(view: View)

    fun hideKeyBoard() {
        val view = activity?.currentFocus ?: View(activity)
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }


    protected fun notifyFontChanged() {
        val fontSize = prefs.get(prefs.fontSize, 16F)
        val fontFamily =
            if (prefs.get(prefs.fontFamily, 0) == 0) null else prefs.get(prefs.fontFamily, 0)
        onCreateFont(fontSize, fontFamily)
    }

    open fun onCreateFont(fontSize: Float, fontFamily: Int? = null) {

    }

}