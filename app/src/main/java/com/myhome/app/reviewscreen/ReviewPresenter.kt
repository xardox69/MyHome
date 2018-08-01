package com.myhome.app.reviewscreen

import com.myhome.app.data.model.Article
import com.myhome.app.data.remote.APIConstants
import com.myhome.app.domain.Params
import com.myhome.app.domain.usecases.GetArticles
import io.reactivex.observers.DisposableObserver

class ReviewPresenter(private var getArticles: GetArticles): ReviewContract.Presenter{


    var params: Params = Params.create()

    init {
        params.putString(APIConstants.APP_DOMAIN, "1")
        params.putString(APIConstants.LOCALE, APIConstants.CURRENT_LOCALE)
        params.putInt(APIConstants.LIMIT, APIConstants.CURENT_LIMIT)
    }


    var mView : ReviewContract.View? = null


    override fun dropView() {
        mView = null
    }

    override fun takeView(view: ReviewContract.View) {
        mView = view
    }


    override fun getArticles() {
        getArticles.execute(ArticlesObserver(),params)
    }


    inner class ArticlesObserver (): DisposableObserver<MutableList<Article>>() {
        override fun onError(e: Throwable) {
        }

        override fun onNext(t: MutableList<Article>) {
            mView?.setData(t)
        }

        override fun onComplete() {
        }

    }











}