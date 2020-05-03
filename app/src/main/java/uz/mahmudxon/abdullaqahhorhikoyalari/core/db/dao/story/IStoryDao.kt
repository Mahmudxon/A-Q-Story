package uz.mahmudxon.abdullaqahhorhikoyalari.core.db.dao.story

import androidx.room.Dao
import androidx.room.Query
import io.reactivex.Observable
import uz.mahmudxon.abdullaqahhorhikoyalari.core.db.model.Story
import uz.mahmudxon.abdullaqahhorhikoyalari.core.db.model.StoryListData

@Dao
interface IStoryDao {
    @Query("select id, title from Story order by title asc")
    fun getAllTitles() : Observable<List<StoryListData>>

    @Query("select id, title from Story where title like :search order by title asc")
    fun searchTitles(search : String) : Observable<List<StoryListData>>


    @Query("select * from Story where id = :id")
    fun getStory(id : Int) : Observable<Story>
}