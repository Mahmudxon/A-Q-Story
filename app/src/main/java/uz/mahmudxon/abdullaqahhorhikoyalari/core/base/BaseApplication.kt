package uz.mahmudxon.abdullaqahhorhikoyalari.core.base

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import uz.mahmudxon.abdullaqahhorhikoyalari.core.di.component.DaggerAppComponent
import uz.mahmudxon.abdullaqahhorhikoyalari.core.util.log

class BaseApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        log("BaseApplication -> applicationInjector()")
        return DaggerAppComponent.builder().application(this).build()
    }
}