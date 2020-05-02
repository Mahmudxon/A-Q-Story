package uz.mahmudxon.abdullaqahhorhikoyalari.ui.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.LayoutRes
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity(@LayoutRes val layoutId : Int) : DaggerAppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        onAfterCreate()
    }

    abstract fun onAfterCreate()
}