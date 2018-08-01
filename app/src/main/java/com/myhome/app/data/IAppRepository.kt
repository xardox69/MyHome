package com.myhome.app.data

import com.myhome.app.data.model.GetItemsResponse
import com.myhome.app.domain.usecases.entities.Params
import io.reactivex.Observable
import retrofit2.Response

public interface IAppRepository{


    fun getItems(params : Params): Observable<Response<GetItemsResponse>>

    fun likeArticle(sku:String)

    fun dislikeArticle(sku:String)

    fun disLikeArticle(sku:String)

    fun getCachedItems()

}