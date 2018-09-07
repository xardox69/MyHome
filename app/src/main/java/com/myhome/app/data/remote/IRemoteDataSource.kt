package com.myhome.app.data.remote

import com.myhome.app.data.model.Article
import com.myhome.app.domain.Params
import io.reactivex.Observable

interface IRemoteDataSource {

    fun getArticles(params: Params): Observable<MutableList<Article>>
}