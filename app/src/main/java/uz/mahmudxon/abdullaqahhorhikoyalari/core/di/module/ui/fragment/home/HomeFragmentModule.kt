package uz.mahmudxon.abdullaqahhorhikoyalari.core.di.module.ui.fragment.home

import dagger.Module
import dagger.Provides
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.home.IHome
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.home.HomeFragment

@Module(includes = [HomePresenterModule::class])
class HomeFragmentModule
{
    @Provides
    fun provideView(fragment: HomeFragment) : IHome.IView = fragment
}