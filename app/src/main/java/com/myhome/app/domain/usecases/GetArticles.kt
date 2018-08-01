package com.myhome.app.domain.usecases

import com.myhome.app.data.AppRepository
import com.myhome.app.data.model.Article
import com.myhome.app.domain.usecases.entities.Params
import io.reactivex.Observable

class GetArticles  constructor(private val repository: AppRepository) : BaseUseCase<MutableList<Article>>() {


    override fun getObservable(params: Params): Observable<MutableList<Article>> {

     return    repository.getCachedItems().flatMap { response->
            if(response!=null && response.size >0){
                (Observable.just(response))
            }else{
                (repository.getItems(params).flatMap { response -> (Observable.just(response.body()!!.objects.articles)) })
            }
        }

    }
}