package uz.mahmudxon.abdullaqahhorhikoyalari.core.lists.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_story.view.*
import uz.mahmudxon.abdullaqahhorhikoyalari.core.db.model.StoryListData
import uz.mahmudxon.abdullaqahhorhikoyalari.core.lists.viewholder.StoryViewHolder
import javax.inject.Inject

class StoryAdapter @Inject constructor() : RecyclerView.Adapter<StoryViewHolder>() {

    private var storyIStoryItemClick: IStoryItemClick? = null
    private var data: ArrayList<StoryListData> = ArrayList()
    var textColor: Int? = null
    var backgrund: Int? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        return StoryViewHolder(parent)
    }

    fun swapData(newData: List<StoryListData>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        holder.itemView.apply {
            title?.text = data[position].title
            this@StoryAdapter.backgrund?.let { setBackgroundColor(it) }
            textColor?.let { title?.setTextColor(it) }
            setOnClickListener {
                this@StoryAdapter.storyIStoryItemClick?.onItemClick(data[position])
            }
        }
    }


    fun setItemClickListener(storyIStoryItemClick: IStoryItemClick) {
        this.storyIStoryItemClick = storyIStoryItemClick
    }

    interface IStoryItemClick {
        fun onItemClick(item: StoryListData)
    }
}
