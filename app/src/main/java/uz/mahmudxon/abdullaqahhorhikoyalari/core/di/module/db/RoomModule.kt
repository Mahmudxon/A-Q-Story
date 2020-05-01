package uz.mahmudxon.abdullaqahhorhikoyalari.core.di.module.db

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import uz.mahmudxon.abdullaqahhorhikoyalari.core.db.AppDatabase
import uz.mahmudxon.abdullaqahhorhikoyalari.core.db.dao.story.IStoryDao

@Module
class RoomModule {
    @Provides
    fun provideDataBase(ctx: Context) =
        Room.databaseBuilder(ctx, AppDatabase::class.java, "story.db")
            .createFromAsset("story.db")
            .build()

    @Provides
    fun provideStoryDao(appDatabase: AppDatabase): IStoryDao = appDatabase.storyDao()
}