package uz.mahmudxon.abdullaqahhorhikoyalari.core.di.module.ui.fragment.home

import dagger.Module
import dagger.Provides
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.fragments.home.HomePresenterImpl
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.fragments.home.IHome

@Module
class HomePresenterModule {
    @Provides
    fun providePresenter(homePresenterImpl: HomePresenterImpl): IHome.IPresenter = homePresenterImpl
}