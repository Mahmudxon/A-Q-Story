package uz.mahmudxon.abdullaqahhorhikoyalari.core.db.dao.story

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import uz.mahmudxon.abdullaqahhorhikoyalari.core.db.AppDatabase
import uz.mahmudxon.abdullaqahhorhikoyalari.core.util.log
import javax.inject.Inject

class StoryImpl @Inject constructor(val dao: IStoryDao) {



    var IStoryCallBack: IStoryCallBack? = null

    fun getAllStory() {
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
            dao.getAllStory().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    IStoryCallBack?.success(it)
                    log(it.size.toString())
                }, {
                    IStoryCallBack?.error(it)
                    log("${it.message}")
                })
        )
    }
}