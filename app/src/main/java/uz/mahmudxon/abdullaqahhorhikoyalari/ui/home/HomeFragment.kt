package uz.mahmudxon.abdullaqahhorhikoyalari.ui.home

import android.content.res.ColorStateList
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.drawer_header_layout.*
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
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.theme.IAnimationThemeChanger
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.theme.Theme
import javax.inject.Inject


class HomeFragment : BaseFagment(R.layout.fragment_home), IHome.IView,
    StoryAdapter.IStoryItemClick, View.OnClickListener, SearchView.OnQueryTextListener,
    NavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var presenter: IHome.IPresenter

    @Inject
    lateinit var listAdapter: StoryAdapter

    @Inject
    lateinit var themeAnimator: IAnimationThemeChanger


    override fun onCreate(view: View) {
        presenter.fetchData()
        list?.adapter = listAdapter
        list?.layoutManager = LinearLayoutManager(context)
        listAdapter.setItemClickListener(this)
        menu.setOnClickListener(this)
        search_view?.setOnQueryTextListener(this)
        search_view?.getCancelIconImageView()?.setOnClickListener(this)
        navigation?.setNavigationItemSelectedListener(this)
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
                findViewById<ImageView>(R.id.portret).setOnClickListener(this@HomeFragment)
                findViewById<TextView>(R.id.name).setOnClickListener(this@HomeFragment)
                findViewById<TextView>(R.id.year).setOnClickListener(this@HomeFragment)
            }
            val states = arrayOf(
                intArrayOf(-android.R.attr.state_enabled),
                intArrayOf(android.R.attr.state_enabled),
                intArrayOf(-android.R.attr.state_checked),
                intArrayOf(android.R.attr.state_pressed)
            )

            val colors = intArrayOf(
                primaryTextColor,
                primaryTextColor,
                primaryTextColor,
                primaryTextColor
            )

            navigation?.itemTextColor = ColorStateList(states, colors)
            navigation?.itemIconTintList = ColorStateList(states, colors)
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
            R.id.portret, R.id.name, R.id.year -> {
                drawer_layout?.closeDrawer(GravityCompat.START)
                navController.navigate(R.id.action_homeFragment_to_aboutAuthorFragment)
            }
        }
    }


    private fun switchTheme() {
        themeAnimator.takeScreenshot()
        val theme = prefs.get(prefs.theme, Theme.THEME_CLASSIC)
        val newId = if (theme == Theme.THEME_NIGHT) prefs.get(
            prefs.stockTheme,
            Theme.THEME_CLASSIC
        ) else Theme.THEME_NIGHT
        prefs.save(prefs.theme, newId)
        notifyThemeChanged()
        val x = (night_mode.x + night_mode.width / 2).toInt()
        val y = (night_mode.y + night_mode.height / 2).toInt()
        themeAnimator.startAnimation(x, y)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        newText?.let { presenter.searchData(it) }
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        drawer_layout?.closeDrawer(GravityCompat.START)

        when (item.itemId) {
            R.id.auth -> {
                navController.navigate(R.id.action_homeFragment_to_aboutAuthorFragment)
            }

            R.id.feedback -> {
                navController.navigate(R.id.action_homeFragment_to_feedbackFragment)
            }

            R.id.setting -> {
                navController.navigate(R.id.action_homeFragment_to_settingsFragment)
            }

            R.id.info -> {
                navController.navigate(R.id.action_homeFragment_to_aboutAppFragment)
            }

            R.id.exit -> {
                activity?.finish()
            }
        }


        return true
    }
}
