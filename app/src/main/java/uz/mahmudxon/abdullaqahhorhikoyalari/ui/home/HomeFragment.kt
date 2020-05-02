package uz.mahmudxon.abdullaqahhorhikoyalari.ui.home

import android.view.View
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
import uz.mahmudxon.abdullaqahhorhikoyalari.R
import uz.mahmudxon.abdullaqahhorhikoyalari.core.db.model.Story
import uz.mahmudxon.abdullaqahhorhikoyalari.core.db.model.StoryListData
import uz.mahmudxon.abdullaqahhorhikoyalari.core.lists.adapter.IStoryItemClick
import uz.mahmudxon.abdullaqahhorhikoyalari.core.lists.adapter.StoryAdapter
import uz.mahmudxon.abdullaqahhorhikoyalari.core.util.log
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.BaseFagment
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.home.IHome
import javax.inject.Inject


class HomeFragment : BaseFagment(R.layout.fragment_home), IHome.IView, IStoryItemClick {

    @Inject
    lateinit var presenter: IHome.IPresenter

    @Inject
    lateinit var listAdapter: StoryAdapter


    override fun onCreate(view: View) {
        presenter.fetchData()
        list?.adapter = listAdapter
        list?.layoutManager = LinearLayoutManager(context)
        listAdapter.setItemClickListener(this)
    }

    override fun loadData(data: List<StoryListData>) {
        listAdapter.swapData(data)
    }

    override fun showError(message: String?) {
        toast(message)
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun onItemClick(item: StoryListData) {
       val bundle = bundleOf("storyId" to item.id)
        navController.navigate(R.id.action_homeFragment_to_storyFragment, bundle)
    }
}
