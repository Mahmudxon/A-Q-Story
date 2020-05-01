package uz.mahmudxon.abdullaqahhorhikoyalari.core.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import uz.mahmudxon.abdullaqahhorhikoyalari.core.base.BaseApplication
import uz.mahmudxon.abdullaqahhorhikoyalari.core.di.module.app.AppModule
import uz.mahmudxon.abdullaqahhorhikoyalari.core.di.module.ui.ActivityBulder

@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, ActivityBulder::class])
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder
    {
        @BindsInstance
        fun application(application: Application) : Builder
        fun build() : AppComponent
    }

}