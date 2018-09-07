package com.myhome.app.data

import com.myhome.app.data.local.ILocalDataSource
import com.myhome.app.data.model.Article
import com.myhome.app.data.remote.IRemoteDataSource
import com.myhome.app.domain.Params
import io.reactivex.Observable
import javax.inject.Inject

open class AppRepository @Inject constructor(private val remoteDataSource: IRemoteDataSource,
                                             private val localDataSource: ILocalDataSource) : IAppRepository {

    override fun getItems(params: Params): Observable<MutableList<Article>> {
        return remoteDataSource.getArticles(params).map { response ->
            if (response.size > 0) {
                localDataSource.saveItems(response)
            }
            (response)
        }
    }

    override fun dislikeArticle(sku: String) {
        localDataSource.dislikeArticle(sku)
    }


    override fun likeArticle(sku: String) {
        localDataSource.likeArticle(sku)
    }

    override fun disLikeArticle(sku: String) {
        localDataSource.dislikeArticle(sku)
    }

    override fun getCachedItems(): Observable<MutableList<Article>> {
        return localDataSource.getItems()
    }


}