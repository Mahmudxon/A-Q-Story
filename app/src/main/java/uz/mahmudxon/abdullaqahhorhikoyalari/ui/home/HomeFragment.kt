package uz.mahmudxon.abdullaqahhorhikoyalari.ui.home

import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
import uz.mahmudxon.abdullaqahhorhikoyalari.R
import uz.mahmudxon.abdullaqahhorhikoyalari.core.db.model.StoryListData
import uz.mahmudxon.abdullaqahhorhikoyalari.core.lists.adapter.StoryAdapter
import uz.mahmudxon.abdullaqahhorhikoyalari.core.util.getAutoComplete
import uz.mahmudxon.abdullaqahhorhikoyalari.core.util.getCancelIconImageView
import uz.mahmudxon.abdullaqahhorhikoyalari.core.util.getIconImageView
import uz.mahmudxon.abdullaqahhorhikoyalari.core.util.setIconColor
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.BaseFagment
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.fragments.home.IHome
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.theme.Classic
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.theme.Theme
import javax.inject.Inject


class HomeFragment : BaseFagment(R.layout.fragment_home), IHome.IView,
    StoryAdapter.IStoryItemClick, View.OnClickListener, SearchView.OnQueryTextListener {

    @Inject
    lateinit var presenter: IHome.IPresenter

    @Inject
    lateinit var listAdapter: StoryAdapter


    override fun onCreate(view: View) {
        presenter.fetchData()
        list?.adapter = listAdapter
        list?.layoutManager = LinearLayoutManager(context)
        listAdapter.setItemClickListener(this)
        menu.setOnClickListener(this)
        search_view?.setOnQueryTextListener(this)
        search_view?.getCancelIconImageView()?.setOnClickListener(this)
    }

    override fun onCreateTheme(theme: Theme) {
        super.onCreateTheme(theme)
        context?.let {
            val primaryTextColor = ContextCompat.getColor(it, theme.primaryTextColor)
            val primaryColor = ContextCompat.getColor(it, theme.primaryColor)
            val primaryColorDark = ContextCompat.getColor(it, theme.primaryColorDark)
            val secondaryText = ContextCompat.getColor(it, theme.secondaryTextColor)
            listAdapter.textColor = primaryTextColor
            listAdapter.backgrund = primaryColor
            listAdapter.notifyDataSetChanged()
            action_bar?.setBackgroundColor(primaryColorDark)
            action_bar_text?.setTextColor(secondaryText)
            navigation?.setBackgroundColor(primaryColor)
            search_view?.getIconImageView()?.setIconColor(secondaryText)
            search_view?.getAutoComplete()?.setTextColor(secondaryText)
            search_view?.getCancelIconImageView()?.setIconColor(secondaryText)
            val navigationheaderView = navigation.getHeaderView(0)
            navigationheaderView?.apply {
                findViewById<ConstraintLayout>(R.id.drawer_header_layout).setBackgroundColor(
                    primaryColorDark
                )
                findViewById<ImageView>(R.id.night_mode).setOnClickListener(this@HomeFragment)
            }
        }
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
        hideKeyBoard()
        val bundle = bundleOf("storyId" to item.id)
        navController.navigate(R.id.action_homeFragment_to_storyFragment, bundle)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.menu -> {
                drawer_layout?.openDrawer(GravityCompat.START)
            }
            androidx.appcompat.R.id.search_close_btn -> {
                activity?.onBackPressed()
            }
            R.id.night_mode -> {
                switchTheme()
            }
        }
    }


    private fun switchTheme()
    {
        val theme = currentTheme ?: Classic()
        val newId =   if (theme.id == Theme.THEME_NIGHT) prefs.get(prefs.stockTheme, Theme.THEME_CLASSIC) else Theme.THEME_NIGHT
        prefs.save(prefs.theme, newId)
        notifyThemeChanged()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        newText?.let { presenter.searchData(it) }
        return true
    }

}
