package com.myhome.app.data.remote

import com.myhome.app.data.model.GetItemsResponse
import com.myhome.app.domain.Params
import io.reactivex.Observable
import retrofit2.Response

interface IRemoteDataSource {

    fun getArticles(params: Params): Observable<Response<GetItemsResponse>>;
}