package com.myhome.app.domain.usecases

import com.myhome.app.data.IAppRepository
import com.myhome.app.domain.Params
import com.myhome.app.domain.entities.ArticleModel
import com.myhome.app.domain.mapper.ArticleMapper
import io.reactivex.Observable
import javax.inject.Inject


class GetArticles @Inject constructor(private val repository: IAppRepository, private val mapper: ArticleMapper)
    : BaseUseCase<MutableList<ArticleModel>>() {


    override fun getObservable(params: Params): Observable<MutableList<ArticleModel>> {

        return repository.getCachedItems().flatMap { response ->
            if (response.size > 0) {
                (Observable.just(mapper.mapFrom(response)))
            } else {

                (repository.getItems(params).flatMap { response ->
                    (Observable.just(mapper.mapFrom(response)))
                })

            }
        }

    }
}