package uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.fragments.story

import android.view.KeyEvent
import uz.mahmudxon.abdullaqahhorhikoyalari.core.db.dao.story.IStoryCallBack
import uz.mahmudxon.abdullaqahhorhikoyalari.core.db.dao.story.StoryImpl
import uz.mahmudxon.abdullaqahhorhikoyalari.core.db.model.Story
import uz.mahmudxon.abdullaqahhorhikoyalari.core.db.model.StoryListData
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.KeyboardCallBack
import javax.inject.Inject

class StoryPresenterImpl @Inject constructor(val view: IStory.IView, val storyImpl: StoryImpl) :
    IStory.IPresenter, IStoryCallBack, KeyboardCallBack {
    init {
        storyImpl.IStoryCallBack = this
    }

    override fun fetchStory(id: Int) {
        view.showLoading()
        storyImpl.getStory(id)
    }

    override fun titles(data: List<StoryListData>) {

    }

    override fun story(story: Story) {
        view.hideLoading()
        view.loadStory(story)
    }

    override fun error(error: Throwable) {
        view.showError(error.message)
    }

    override fun dispatchKeyEvent(event: KeyEvent?, default: Boolean): Boolean {
        return when (event?.keyCode) {
            KeyEvent.KEYCODE_VOLUME_DOWN -> {
                view.scrollDown()
                return true
            }

            KeyEvent.KEYCODE_VOLUME_UP -> {
                view.scrollUp()
                return true
            }
            else -> default
        }
    }
}
