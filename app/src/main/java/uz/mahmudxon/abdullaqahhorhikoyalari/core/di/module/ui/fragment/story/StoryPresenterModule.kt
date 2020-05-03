package uz.mahmudxon.abdullaqahhorhikoyalari.core.di.module.ui.fragment.story

import dagger.Module
import dagger.Provides
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.fragments.story.IStory
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.fragments.story.StoryPresenterImpl

@Module
class StoryPresenterModule
{
    @Provides
    fun providePresenter(p : StoryPresenterImpl) : IStory.IPresenter = p
}