package uz.mahmudxon.abdullaqahhorhikoyalari.core.db.dao.story

import androidx.room.Dao
import androidx.room.Query
import io.reactivex.Observable
import uz.mahmudxon.abdullaqahhorhikoyalari.core.db.model.Story

@Dao
interface IStoryDao {
    @Query("select * from Story")
    fun getAllStory() : Observable<List<Story>>
}