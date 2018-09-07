package com.myhome.app.itemslist

import com.myhome.app.data.remote.APIConstants
import com.myhome.app.domain.Params
import com.myhome.app.domain.entities.ArticleModel
import com.myhome.app.domain.usecases.GetArticles
import com.myhome.app.domain.usecases.UpdateArticle
import io.reactivex.Scheduler
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject


/**
 * Presenter to show the items in a pager
 */
class ArticlePagerPresenter @Inject constructor(private var getArticles: GetArticles, private var updateArticle: UpdateArticle,
                                                private var subscriberScheduler: Scheduler, private var observerScheduler: Scheduler) : ArticlePagerContract.Presenter {


    override fun setNextpage(currentPage: Int, totalPages: Int) {
        if (currentPage < totalPages) {
            mView?.showNextPage(currentPage + 1)
        } else {
            mView?.showNoItemsLeft()
        }
    }

    override fun updateRatings() {
        getArticles.execute(ArticlesObserver(false), params, subscriberScheduler, observerScheduler)
    }

    override fun likeArticle(sku: String) {

        params.putInt(STATE_KEY, LIKE_STATE_VAL)
        params.putString(ARTICLE_SKU_KEY, sku)
        updateArticle.execute(ArticlesStateObserver(), params, subscriberScheduler, observerScheduler)
    }

    override fun dislikeArticle(sku: String) {
        params.putInt(STATE_KEY, DISLIKE_STATE_KEY)
        params.putString(ARTICLE_SKU_KEY, sku)
        updateArticle.execute(ArticlesStateObserver(), params, subscriberScheduler, observerScheduler)
    }


    private var params: Params = Params.create()


    override fun getArticles() {
        mView?.showLoading()
        getArticles.execute(ArticlesObserver(true), params, subscriberScheduler, observerScheduler)
    }

    init {
        params.putString(APIConstants.APP_DOMAIN, APIConstants.CURRENT_DOMAIN)
        params.putString(APIConstants.LOCALE, APIConstants.CURRENT_LOCALE)
        params.putInt(APIConstants.LIMIT, APIConstants.CURRENT_LIMIT)
    }


    private var mView: ArticlePagerContract.View? = null


    override fun dropView() {
        mView = null
    }


    override fun takeView(view: ArticlePagerContract.View) {
        mView = view
    }


    companion object {
        const val STATE_KEY: String = "like"
        const val ARTICLE_SKU_KEY: String = "sku"
        const val LIKE_STATE_VAL: Int = 0
        const val DISLIKE_STATE_KEY: Int = 1
    }


    override fun pageChanged(currentPage: Int, totalPages: Int) {
        if (currentPage == totalPages - 1) {
            mView?.showNoItemsLeft()
        }
    }


    private inner class ArticlesObserver(private var updateItems: Boolean) : DisposableObserver<MutableList<ArticleModel>>() {
        override fun onError(e: Throwable) {
            mView?.showNetworkError()
        }

        override fun onNext(t: MutableList<ArticleModel>) {
            mView?.onDataLoaded()
            var rating = 0
            val total = t.size
            if (updateItems) {
                mView?.setData(t)
            }

            for (item in t) {
                if (item.dislike || item.like) {
                    rating++
                }
            }

            mView?.updateRatings(rating, total)
            if (rating == total) {
                mView?.enableReviewButton()
            } else {
                mView?.disableReviewButton()
            }

        }

        override fun onComplete() {
        }

    }


    private inner class ArticlesStateObserver : DisposableObserver<Boolean>() {
        override fun onError(e: Throwable) {
        }

        override fun onNext(t: Boolean) {
        }

        override fun onComplete() {
        }

    }
}