package uz.mahmudxon.abdullaqahhorhikoyalari.core.di.module.ui

import dagger.Module
import dagger.android.ContributesAndroidInjector
import uz.mahmudxon.abdullaqahhorhikoyalari.core.di.module.ui.activity.main.MainActivityFragmentProvider
import uz.mahmudxon.abdullaqahhorhikoyalari.core.di.module.ui.activity.main.MainActivityModule
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.MainActivity

@Module
abstract class ActivityBulder
{
    @ContributesAndroidInjector(modules = [MainActivityModule::class, MainActivityFragmentProvider::class])
    abstract fun contributeMainActivity() : MainActivity
}