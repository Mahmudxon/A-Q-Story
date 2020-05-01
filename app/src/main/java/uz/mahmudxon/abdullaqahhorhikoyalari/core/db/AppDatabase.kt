package uz.mahmudxon.abdullaqahhorhikoyalari.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.mahmudxon.abdullaqahhorhikoyalari.core.db.dao.story.IStoryDao
import uz.mahmudxon.abdullaqahhorhikoyalari.core.db.model.Story

@Database(entities = [Story::class], version = 1)
abstract class AppDatabase : RoomDatabase()
{
    abstract fun storyDao() : IStoryDao
}
