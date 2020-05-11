package uz.mahmudxon.abdullaqahhorhikoyalari.ui.story

import android.annotation.SuppressLint
import android.view.View
import android.widget.ScrollView
import androidx.core.content.ContextCompat
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.fragment_story.*
import kotlinx.android.synthetic.main.fragment_story.adView
import uz.mahmudxon.abdullaqahhorhikoyalari.R
import uz.mahmudxon.abdullaqahhorhikoyalari.core.db.model.Story
import uz.mahmudxon.abdullaqahhorhikoyalari.core.dialog.SettingsDialog
import uz.mahmudxon.abdullaqahhorhikoyalari.core.util.setIconColor
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.MainActivity
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.BaseFagment
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.fragments.story.IStory
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.fragments.story.StoryPresenterImpl
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.theme.Theme
import javax.inject.Inject

class StoryFragment : BaseFagment(R.layout.fragment_story), IStory.IView, View.OnClickListener,
    SettingsDialog.ISettingsChangeListener {

    @Inject
    lateinit var presenter: StoryPresenterImpl

    @Inject
    lateinit var settingsDialog: SettingsDialog

    override fun onCreate(view: View) {
        val bundle = arguments

        bundle?.let {
            val id = it["storyId"] as Int
            presenter.fetchStory(id)
            back?.setOnClickListener(this)
            setting?.setOnClickListener(this)
        }
        settingsDialog.listener = this
        if (prefs.get(prefs.useValumeKey, false))
            activity?.let { (it as MainActivity).keyboardCallBack = presenter }

        MobileAds.initialize(requireContext()) {}
        val adRequest = AdRequest.Builder().build()
        adView?.loadAd(adRequest)
    }

    override fun onCreateTheme(theme: Theme) {
        super.onCreateTheme(theme)
        context?.let {
            val primaryTextColor = ContextCompat.getColor(it, theme.primaryTextColor)
            val secondaryTextColor = ContextCompat.getColor(it, theme.secondaryTextColor)
            val primaryColorDark = ContextCompat.getColor(it, theme.primaryColorDark)
            title?.setTextColor(primaryTextColor)
            additional?.setTextColor(primaryTextColor)
            epic?.setTextColor(primaryTextColor)
            epicAuth?.setTextColor(primaryTextColor)
            story?.setTextColor(primaryTextColor)
            year?.setTextColor(primaryTextColor)
            action_bar_layout?.setBackgroundColor(primaryColorDark)
            back?.setIconColor(secondaryTextColor)
            action_bar_title?.setTextColor(secondaryTextColor)
            setting?.setIconColor(secondaryTextColor)

        }
    }

    override fun onCreateFont(fontSize: Float, fontFamily: Int?) {
        super.onCreateFont(fontSize, fontFamily)
        title?.textSize = fontSize + 2
        additional?.textSize = fontSize
        epic?.textSize = fontSize
        epicAuth?.textSize = fontSize
        story?.textSize = fontSize
    }

    @SuppressLint("SetTextI18n")
    private fun showStory(data: Story) {
        title?.text = data.title
        action_bar_title?.text = "Abdulla Qahhor - ${data.title}".toUpperCase()
        data.additional?.let {
            additional?.text = it
            additional?.visibility = View.VISIBLE
        }


        data.epic?.let {
            epic?.text = it
            epic?.visibility = View.VISIBLE
        }



        data.authEpic?.let {
            epicAuth?.visibility = View.VISIBLE
            epicAuth?.text = it
        }

        story?.text = data.story

        data.year?.let {
            year?.text = it.toString()
        }
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun loadStory(story: Story) {
        showStory(story)
    }

    override fun showError(message: String?) {
        toast(message)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.back -> {
                activity?.onBackPressed()
            }
            R.id.setting -> {
                settingsDialog.show()
            }
        }
    }

    override fun onThemeChange() {
        notifyThemeChanged()
    }

    override fun onFontChange() {
        notifyFontChanged()
    }


    override fun onDestroy() {
        activity?.let { (it as MainActivity).keyboardCallBack = null }
        super.onDestroy()
    }

    override fun scrollUp() {
        scrollView?.arrowScroll(ScrollView.FOCUS_UP)
    }

    override fun scrollDown() {
        scrollView?.arrowScroll(ScrollView.FOCUS_DOWN)
    }
}
