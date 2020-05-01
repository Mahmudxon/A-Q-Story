package uz.mahmudxon.abdullaqahhorhikoyalari.ui.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
import uz.mahmudxon.abdullaqahhorhikoyalari.R
import uz.mahmudxon.abdullaqahhorhikoyalari.core.db.model.Story
import uz.mahmudxon.abdullaqahhorhikoyalari.core.lists.adapter.StoryAdapter
import uz.mahmudxon.abdullaqahhorhikoyalari.core.util.log
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.BaseFagment
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.home.IHome
import javax.inject.Inject


class HomeFragment : BaseFagment(R.layout.fragment_home), IHome.IView {

    @Inject
    lateinit var presenter: IHome.IPresenter

    @Inject
    lateinit var listAdapter : StoryAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        log("onViewCreated(view: View, savedInstanceState: Bundle?)")
    }


    override fun onCreate(view: View) {
        presenter.fetchData()
        list?.adapter = listAdapter
        list?.layoutManager = LinearLayoutManager(context)
        log("Home fragment OnCreate")
    }

    override fun loadData(data: List<Story>) {
        listAdapter.swapData(data)
        toast("no data")
    }

    override fun showError(message: String?) {
        toast(message)
    }

    override fun showLoading() {
        toast("loading")
    }

    override fun hideLoading() {

    }
}
