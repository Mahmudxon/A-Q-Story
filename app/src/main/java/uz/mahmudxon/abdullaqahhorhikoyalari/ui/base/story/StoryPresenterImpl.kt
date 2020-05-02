package uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.story

import uz.mahmudxon.abdullaqahhorhikoyalari.core.db.dao.story.IStoryCallBack
import uz.mahmudxon.abdullaqahhorhikoyalari.core.db.dao.story.StoryImpl
import uz.mahmudxon.abdullaqahhorhikoyalari.core.db.model.Story
import uz.mahmudxon.abdullaqahhorhikoyalari.core.db.model.StoryListData
import javax.inject.Inject

class StoryPresenterImpl @Inject constructor(val view: IStory.IView, val storyImpl: StoryImpl) :
    IStory.IPresenter, IStoryCallBack {
    init {
        storyImpl.IStoryCallBack = this
    }

    override fun fetchStory(id: Int) {
        view.showLoading()
        storyImpl.getStory(id)
    }

    override fun titles(data: List<StoryListData>) {
        TODO("Not yet implemented")
    }

    override fun story(story: Story) {
        view.hideLoading()
        view.loadStory(story)
    }

    override fun error(error: Throwable) {
        view.showError(error.message)
    }
}
