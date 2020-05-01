package uz.mahmudxon.abdullaqahhorhikoyalari.core.db.dao.story

import uz.mahmudxon.abdullaqahhorhikoyalari.core.db.model.Story

interface IStoryCallBack {
    fun success(data: List<Story>)
    fun error(error : Throwable)
}