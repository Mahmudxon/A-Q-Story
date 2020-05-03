package uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.fragments.home

import uz.mahmudxon.abdullaqahhorhikoyalari.core.db.dao.story.IStoryCallBack
import uz.mahmudxon.abdullaqahhorhikoyalari.core.db.dao.story.StoryImpl
import uz.mahmudxon.abdullaqahhorhikoyalari.core.db.model.Story
import uz.mahmudxon.abdullaqahhorhikoyalari.core.db.model.StoryListData
import uz.mahmudxon.abdullaqahhorhikoyalari.core.util.log
import javax.inject.Inject

class HomePresenterImpl @Inject constructor(val storyImpl: StoryImpl, val view: IHome.IView) :
    IHome.IPresenter, IStoryCallBack {

    init {
        storyImpl.IStoryCallBack = this
        log("Home Presenter init function")
    }

    override fun fetchData() {
        view.showLoading()
        log("Home Presenter fetchData() function")
        storyImpl.getAllTitles()
    }

    override fun searchData(search: String) {
        view.showLoading()
        storyImpl.searchTitles(search)
    }

    override fun titles(data: List<StoryListData>) {
        view.hideLoading()
        view.loadData(data)
    }

    override fun story(story: Story) {
        TODO("Not yet implemented")
    }

    override fun error(error: Throwable) {
        view.hideLoading()
        view.showError(error.message)
    }
}