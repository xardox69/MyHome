package com.myhome.app.domain.usecases

import com.myhome.app.data.IAppRepository
import com.myhome.app.data.model.Article
import com.myhome.app.domain.Params
import io.reactivex.Observable
import javax.inject.Inject


@Suppress("NAME_SHADOWING")
class GetArticles @Inject constructor(private val repository: IAppRepository) : BaseUseCase<MutableList<Article>>() {



    override fun getObservable(params: Params): Observable<MutableList<Article>> {

     return    repository.getCachedItems().flatMap { response->
            if(response.size >0){
                (Observable.just(response))
            } else {
                (repository.getItems(params).flatMap { response -> (Observable.just(response.body()!!.objects.articles)) })
            }
        }

    }
}