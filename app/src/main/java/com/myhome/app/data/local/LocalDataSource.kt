package com.myhome.app.data.local

import com.myhome.app.data.local.room.DataDao
import com.myhome.app.data.model.Article
import io.reactivex.Observable
import io.reactivex.Scheduler
import org.jetbrains.anko.doAsync

/**
 * Local data source to store and save data locally
 */
class LocalDataSource constructor(private val mDataDao: DataDao, private val subscriberScheduler: Scheduler,
                                  private val observerScheduler: Scheduler) : ILocalDataSource {




    override fun getItems(): Observable<MutableList<Article>> {
           return  Observable.just(mDataDao.getArticles()).subscribeOn(subscriberScheduler).observeOn(observerScheduler)
    }

    override fun dislikeArticle(sku: String) {
        doAsync {
            mDataDao.likeArticle(false, sku, true)
        }
    }

    override fun likeArticle(sku: String) {
        doAsync {
            mDataDao.likeArticle(true, sku, false)
        }
    }

    override fun saveItems(items: MutableList<Article>) {
        doAsync {
            for (item in items) {
                mDataDao.insertUser(item)
            }
        }
    }

    override fun saveItem(item: Article) {
        doAsync {
        mDataDao.insertUser(item)
        }
    }



}