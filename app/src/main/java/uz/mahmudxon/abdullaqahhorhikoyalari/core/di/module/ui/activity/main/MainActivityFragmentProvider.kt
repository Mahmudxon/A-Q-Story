package uz.mahmudxon.abdullaqahhorhikoyalari.core.di.module.ui.activity.main

import dagger.Module
import dagger.android.ContributesAndroidInjector
import uz.mahmudxon.abdullaqahhorhikoyalari.core.di.module.ui.fragment.home.HomeFragmentModule
import uz.mahmudxon.abdullaqahhorhikoyalari.core.di.module.ui.fragment.story.StoryFragmentModule
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.home.HomeFragment
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.story.StoryFragment

@Module
abstract class MainActivityFragmentProvider {
    @ContributesAndroidInjector(modules = [HomeFragmentModule::class])
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector(modules = [StoryFragmentModule::class])
    abstract fun contributeStoryFragment(): StoryFragment
}