package uz.mahmudxon.abdullaqahhorhikoyalari.core.di.module.ui.activity.main

import dagger.Module
import dagger.android.ContributesAndroidInjector
import uz.mahmudxon.abdullaqahhorhikoyalari.core.di.module.ui.fragment.about_app.AboutAppFragmentModule
import uz.mahmudxon.abdullaqahhorhikoyalari.core.di.module.ui.fragment.about_author.AboutAuthoFragmentModele
import uz.mahmudxon.abdullaqahhorhikoyalari.core.di.module.ui.fragment.feedback.FeedbackFragmentModule
import uz.mahmudxon.abdullaqahhorhikoyalari.core.di.module.ui.fragment.home.HomeFragmentModule
import uz.mahmudxon.abdullaqahhorhikoyalari.core.di.module.ui.fragment.settings.SettingsFragmentModule
import uz.mahmudxon.abdullaqahhorhikoyalari.core.di.module.ui.fragment.story.StoryFragmentModule
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.about_app.AboutAppFragment
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.about_author.AboutAuthorFragment
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.feedback.FeedbackFragment
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.home.HomeFragment
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.settings.SettingsFragment
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.story.StoryFragment
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.webview.WebviewFragment

@Module
abstract class MainActivityFragmentProvider {
    @ContributesAndroidInjector(modules = [HomeFragmentModule::class])
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector(modules = [StoryFragmentModule::class])
    abstract fun contributeStoryFragment(): StoryFragment

    @ContributesAndroidInjector(modules = [AboutAuthoFragmentModele::class])
    abstract fun contributeAboutAuthFragment(): AboutAuthorFragment

    @ContributesAndroidInjector(modules = [FeedbackFragmentModule::class])
    abstract fun contributeFeedbackFragment(): FeedbackFragment

    @ContributesAndroidInjector(modules = [SettingsFragmentModule::class])
    abstract fun contributeSettingsFragment(): SettingsFragment

    @ContributesAndroidInjector(modules = [AboutAppFragmentModule::class])
    abstract fun contributeAboutAppFragment(): AboutAppFragment

    @ContributesAndroidInjector
    abstract fun contributeWebViewFragment() : WebviewFragment

}