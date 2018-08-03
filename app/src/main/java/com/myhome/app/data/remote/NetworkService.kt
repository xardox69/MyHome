package com.myhome.app.data.remote

import com.myhome.app.data.model.GetItemsResponse
import com.myhome.app.data.remote.APIConstants.Companion.APP_DOMAIN
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface NetworkService {


    @GET("categories/100/articles")
    fun getArticles(@Query(APP_DOMAIN) appDomain: String, @Query(APIConstants.LOCALE) locale: String,
                    @Query(APIConstants.LIMIT) limit: Int): Observable<Response<GetItemsResponse>>

}