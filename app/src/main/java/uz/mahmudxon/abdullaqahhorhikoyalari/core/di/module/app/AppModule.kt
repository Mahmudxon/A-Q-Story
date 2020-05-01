package uz.mahmudxon.abdullaqahhorhikoyalari.core.di.module.app

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import uz.mahmudxon.abdullaqahhorhikoyalari.core.di.module.db.RoomModule

@Module(includes = [RoomModule::class])
object AppModule {
    @Provides
    fun provideContext(app: Application): Context = app.applicationContext
}