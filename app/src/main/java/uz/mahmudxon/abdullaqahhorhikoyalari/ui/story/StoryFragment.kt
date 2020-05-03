package uz.mahmudxon.abdullaqahhorhikoyalari.ui.story

import android.view.View
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.fragment_story.*
import uz.mahmudxon.abdullaqahhorhikoyalari.R
import uz.mahmudxon.abdullaqahhorhikoyalari.core.db.model.Story
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.BaseFagment
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.fragments.story.IStory
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.theme.Theme
import javax.inject.Inject

class StoryFragment : BaseFagment(R.layout.fragment_story), IStory.IView {

    @Inject
    lateinit var presenter: IStory.IPresenter

    override fun onCreate(view: View) {
        val bundle = arguments
        bundle?.let {
            val id = it["storyId"] as Int
            presenter.fetchStory(id)
        }
    }

    override fun onCreateTheme(theme: Theme) {
        super.onCreateTheme(theme)
        context?.let {
            val primaryTextColor = ContextCompat.getColor(it, theme.primaryTextColor)
            title?.setTextColor(primaryTextColor)
            additional?.setTextColor(primaryTextColor)
            epic?.setTextColor(primaryTextColor)
            epicAuth?.setTextColor(primaryTextColor)
            story?.setTextColor(primaryTextColor)
            year?.setTextColor(primaryTextColor)
        }
    }

    private fun showStory(data: Story) {
        title?.text = data.title

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
}
