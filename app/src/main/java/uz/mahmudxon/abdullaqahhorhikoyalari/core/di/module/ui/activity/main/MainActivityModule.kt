package uz.mahmudxon.abdullaqahhorhikoyalari.core.di.module.ui.activity.main

import dagger.Module
import dagger.Provides
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.MainActivity
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.theme.IAnimationThemeChanger

@Module
class MainActivityModule {
    @Provides
    fun provideThemeAnimator(mainActivity: MainActivity): IAnimationThemeChanger = mainActivity
}