package uz.mahmudxon.abdullaqahhorhikoyalari.core.lists.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_story.view.*
import uz.mahmudxon.abdullaqahhorhikoyalari.core.db.model.Story
import uz.mahmudxon.abdullaqahhorhikoyalari.core.lists.adapter.IStoryItemClick
import uz.mahmudxon.abdullaqahhorhikoyalari.core.lists.adapter.StoryAdapter

class StoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    var storyItemClick : IStoryItemClick?= null


    fun bindData(data : Story)
    {
        itemView.apply {
            title?.text = data.title
            setOnClickListener {
                storyItemClick?.onItemClick(data)
            }
        }
    }


}