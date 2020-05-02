package uz.mahmudxon.abdullaqahhorhikoyalari.core.db.dao.story

import uz.mahmudxon.abdullaqahhorhikoyalari.core.db.model.Story
import uz.mahmudxon.abdullaqahhorhikoyalari.core.db.model.StoryListData

interface IStoryCallBack {
    fun titles(data: List<StoryListData>)
    fun story(story: Story)
    fun error(error : Throwable)
}