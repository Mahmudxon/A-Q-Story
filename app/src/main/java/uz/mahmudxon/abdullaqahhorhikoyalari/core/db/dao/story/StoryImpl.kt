package uz.mahmudxon.abdullaqahhorhikoyalari.core.db.dao.story

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import uz.mahmudxon.abdullaqahhorhikoyalari.core.db.AppDatabase
import uz.mahmudxon.abdullaqahhorhikoyalari.core.util.log
import javax.inject.Inject

class StoryImpl @Inject constructor(val dao: IStoryDao) {



    var IStoryCallBack: IStoryCallBack? = null

    fun getAllTitles()
    {
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
            dao.getAllTitles().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    IStoryCallBack?.titles(it)
                }, {
                    IStoryCallBack?.error(it)
                })
        )
    }

    fun getStory (id : Int)
    {
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
            dao.getStory(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    IStoryCallBack?.story(it)
                }, {
                    IStoryCallBack?.error(it)
                })
        )
    }
}