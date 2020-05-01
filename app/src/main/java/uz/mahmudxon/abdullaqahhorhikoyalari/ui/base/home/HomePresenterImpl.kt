package uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.home

import uz.mahmudxon.abdullaqahhorhikoyalari.core.db.dao.story.IStoryCallBack
import uz.mahmudxon.abdullaqahhorhikoyalari.core.db.dao.story.StoryImpl
import uz.mahmudxon.abdullaqahhorhikoyalari.core.db.model.Story
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
        storyImpl.getAllStory()
    }

    override fun success(data: List<Story>) {
        log("Home Presenter success($data: List<Story>) function")
        view.hideLoading()
        view.loadData(data)
    }

    override fun error(error: Throwable) {
        view.hideLoading()
        view.showError(error.message)
    }
}