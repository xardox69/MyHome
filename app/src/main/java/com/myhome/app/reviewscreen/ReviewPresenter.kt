package com.myhome.app.reviewscreen

import com.myhome.app.data.model.Article
import com.myhome.app.data.remote.APIConstants
import com.myhome.app.domain.Params
import com.myhome.app.domain.entities.ArticleModel
import com.myhome.app.domain.usecases.GetArticles
import io.reactivex.Scheduler
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

/**
 * Presenter for the review screen
 */
class ReviewPresenter @Inject constructor(private var getArticles: GetArticles, private var subscriberScheduler: Scheduler, private var observerScheduler: Scheduler) : ReviewContract.Presenter {


    private var params: Params = Params.create()

    init {
        params.putString(APIConstants.APP_DOMAIN, APIConstants.CURRENT_DOMAIN)
        params.putString(APIConstants.LOCALE, APIConstants.CURRENT_LOCALE)
        params.putInt(APIConstants.LIMIT, APIConstants.CURRENT_LIMIT)
    }


    private var mView: ReviewContract.View? = null


    override fun dropView() {
        mView = null
    }

    override fun takeView(view: ReviewContract.View) {
        mView = view
    }


    override fun getArticles() {
        mView?.showLoading()
        getArticles.execute(ArticlesObserver(), params, subscriberScheduler, observerScheduler)
    }


    private inner class ArticlesObserver : DisposableObserver<MutableList<ArticleModel>>() {
        override fun onError(e: Throwable) {
            mView?.showNetworkError()
        }

        override fun onNext(t: MutableList<ArticleModel>) {
            mView?.dismissLoading()
            mView?.setData(t)
        }

        override fun onComplete() {
        }

    }


}