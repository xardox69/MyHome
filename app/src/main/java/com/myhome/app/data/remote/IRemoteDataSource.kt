package com.myhome.app.data.remote

import com.myhome.app.data.model.GetItemsResponse
import io.reactivex.Observable
import retrofit2.Response

interface IRemoteDataSource {

    fun getArticles(): Observable<Response<GetItemsResponse>>;
}