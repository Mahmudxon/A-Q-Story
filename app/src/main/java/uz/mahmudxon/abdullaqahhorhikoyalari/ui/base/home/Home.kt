package uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.home

import uz.mahmudxon.abdullaqahhorhikoyalari.core.db.model.Story

interface IHome
{
    interface IView {
        fun loadData(data : List<Story>)
        fun showError(message : String?)
        fun showLoading()
        fun hideLoading()
    }

    interface IPresenter {
        fun fetchData()
    }
}