package uz.mahmudxon.abdullaqahhorhikoyalari.core.di.module.ui.fragment.story

import dagger.Module
import dagger.Provides
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.fragments.story.IStory
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.story.StoryFragment

@Module(includes = [StoryPresenterModule::class])
class StoryFragmentModule
{
    @Provides
    fun provideStoryView(f : StoryFragment) : IStory.IView = f
}