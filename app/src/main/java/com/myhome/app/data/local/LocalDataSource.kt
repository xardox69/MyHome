package com.myhome.app.data.local

import com.myhome.app.data.local.room.DataDao
import com.myhome.app.data.model.Article
import io.reactivex.Observable
import java.util.*

/**
 * Local data source to store and save data locally
 */
class LocalDataSource constructor(private val mDataDao: DataDao) : ILocalDataSource {
    override fun getItems(): Observable<MutableList<Article>> {
        return Observable.just(mDataDao.getArticles())
    }

    override fun dislikeArticle(sku: String) {
        mDataDao.likeArticle(false, sku, true)
    }

    override fun likeArticle(sku: String) {
        mDataDao.likeArticle(true, sku, false)
    }

    override fun saveItems(items: MutableList<Article>) {
        for (item in items) {
            mDataDao.insertUser(item)
        }
    }


    companion object {

        @Volatile
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(mDataDao: DataDao): LocalDataSource {
            if (INSTANCE == null) {
                INSTANCE = LocalDataSource(mDataDao)

            }
            return INSTANCE as LocalDataSource
        }
    }


    override fun saveItem(item: Article) {
        mDataDao.insertUser(item)
    }

    override fun getItems(sku: String): Observable<Article> {
        return Observable.just(mDataDao.getArticleWithSKU(sku));
    }


}