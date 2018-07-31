package com.myhome.app.data

import com.myhome.app.data.local.LocalDataSource
import com.myhome.app.data.model.GetItemsResponse
import com.myhome.app.data.remote.RemoteDataSource
import io.reactivex.Observable
import retrofit2.Response
import java.util.*

class AppRepository  constructor(private val remoteDataSource: RemoteDataSource,
                                private val localDataSource: LocalDataSource): IAppRepository  {
    override fun dislikeArticle(sku: String) {
       localDataSource.dislikeArticle(sku)
    }


    override fun likeArticle(sku: String) {
        localDataSource.likeArticle(sku)
    }

    override fun disLikeArticle(sku: String) {
        localDataSource.dislikeArticle(sku)
    }

    override fun getCachedItems() {
        localDataSource.getItems()
    }

    override fun getItems(): Observable<Response<GetItemsResponse>>{
       return  remoteDataSource.getArticles().map { response ->
            if (response.isSuccessful) {
                localDataSource.saveItems(response.body()!!.articles)
            }
           (response)
        }
    }



}