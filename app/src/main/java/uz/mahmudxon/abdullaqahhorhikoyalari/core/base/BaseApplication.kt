package uz.mahmudxon.abdullaqahhorhikoyalari.core.base

import android.content.Context
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import uz.mahmudxon.abdullaqahhorhikoyalari.core.di.component.DaggerAppComponent

class BaseApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}