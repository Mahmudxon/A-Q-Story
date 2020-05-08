package uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.fragments.story

import uz.mahmudxon.abdullaqahhorhikoyalari.core.db.model.Story

interface IStory {
    interface IView {
        fun showLoading()
        fun hideLoading()
        fun loadStory(story: Story)
        fun showError(message: String?)
        fun scrollUp()
        fun scrollDown()
    }

    interface IPresenter {
        fun fetchStory(id: Int)

    }
}