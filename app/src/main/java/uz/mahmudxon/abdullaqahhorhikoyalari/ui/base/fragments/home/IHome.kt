package uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.fragments.home

import uz.mahmudxon.abdullaqahhorhikoyalari.core.db.model.StoryListData

interface IHome {
    interface IView {
        fun loadData(data: List<StoryListData>)
        fun showError(message: String?)
        fun showLoading()
        fun hideLoading()
    }

    interface IPresenter {
        fun fetchData()
        fun searchData(search : String)
    }
}