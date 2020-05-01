package uz.mahmudxon.abdullaqahhorhikoyalari.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.navigation.NavController
import androidx.navigation.Navigation
import dagger.android.support.DaggerFragment

abstract class BaseFagment(@LayoutRes val layoutId: Int) : DaggerFragment() {

    lateinit var navController: NavController

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
    }

    fun toast(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    fun toast(@StringRes message: Int) {
        toast(context?.getString(message))
    }

    abstract fun onCreate(view: View)
}