package uz.mahmudxon.abdullaqahhorhikoyalari.core.lists.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.mahmudxon.abdullaqahhorhikoyalari.R
import uz.mahmudxon.abdullaqahhorhikoyalari.core.db.model.Story
import uz.mahmudxon.abdullaqahhorhikoyalari.core.db.model.StoryListData
import uz.mahmudxon.abdullaqahhorhikoyalari.core.lists.viewholder.StoryViewHolder
import javax.inject.Inject

class StoryAdapter @Inject constructor() : RecyclerView.Adapter<StoryViewHolder>(), IStoryItemClick {

    private var storyIStoryItemClick : IStoryItemClick ?= null
    private var data: ArrayList<StoryListData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_story, parent, false)
        return StoryViewHolder(view)
    }

    fun swapData(newData: List<StoryListData>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        holder.bindData(data[position])
        holder.storyItemClick = this
    }



    fun setItemClickListener(storyIStoryItemClick: IStoryItemClick)
    {
        this.storyIStoryItemClick = storyIStoryItemClick
    }

    override fun onItemClick(item: StoryListData) {
        this.storyIStoryItemClick?.onItemClick(item)
    }
}

interface IStoryItemClick {
    fun onItemClick(item : StoryListData)
}