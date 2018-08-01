package com.myhome.app.itemslist

import android.os.Bundle
import android.util.Log
import com.myhome.app.data.model.Article
import com.myhome.app.data.remote.APIConstants
import com.myhome.app.domain.usecases.GetArticles
import com.myhome.app.domain.usecases.UpdateArticle
import com.myhome.app.domain.Params
import io.reactivex.Scheduler
import io.reactivex.observers.DisposableObserver

class ArticlePagerPresenter (private var getArticles: GetArticles,private var updateArticle: UpdateArticle,
private var subscriberScheduler:Scheduler, private var observerScheduler: Scheduler): ArticlePagerContract.Presenter{



    override fun setNextpage(currentPage: Int,totalPages: Int) {
            if(currentPage< totalPages){
                    mView?.showNextPage(currentPage+1)
            }else{
                mView?.showNoItemsLeft()
            }
    }

    override fun updateRatings() {
        getArticles.execute(ArticlesObserver(false),params,subscriberScheduler,observerScheduler)
    }

    override fun likeArticle(sku: String) {

        params.putInt(STATE_KEY,LIKE_STATE_VAL)
        params.putString(ARTICLE_SKU_KEY,sku)
        updateArticle.execute(ArticlesStateObserver(),params,subscriberScheduler,observerScheduler)
    }

    override fun dislikeArticle(sku: String) {
        params.putInt(STATE_KEY, DISLIKE_STATE_KEY)
        params.putString(ARTICLE_SKU_KEY,sku)
        updateArticle.execute(ArticlesStateObserver(),params,subscriberScheduler,observerScheduler)
    }


    var params: Params = Params.create()


    override fun getArticles() {
        mView?.showLoading()
        getArticles.execute(ArticlesObserver(true),params,subscriberScheduler,observerScheduler)
    }

    init {
        params.putString(APIConstants.APP_DOMAIN, "1")
        params.putString(APIConstants.LOCALE, APIConstants.CURRENT_LOCALE)
        params.putInt(APIConstants.LIMIT, APIConstants.CURENT_LIMIT)
    }


    var mView :ArticlePagerContract.View? = null


    override fun dropView() {
        mView = null
    }

    override fun saveState(outState: Bundle) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCachedState(savedInstanceState: Bundle) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCachedArticles() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun takeView(view: ArticlePagerContract.View) {
        mView = view
    }


    companion object {
         const val  STATE_KEY :String = "like"
        const val  ARTICLE_SKU_KEY :String = "sku"
            const val  LIKE_STATE_VAL :Int = 0
        const val  DISLIKE_STATE_KEY :Int = 1
    }


    override fun pageChanged(currentPage: Int, totalPages: Int) {
        if(currentPage == totalPages-1){
            mView?.showNoItemsLeft()
        }
    }


    inner class ArticlesObserver (var updateItems: Boolean): DisposableObserver<MutableList<Article>>() {
        override fun onError(e: Throwable) {
        }

        override fun onNext(t: MutableList<Article>) {
            mView?.onDataLoaded()
            var rating :Int = 0;
            var total :Int = t.size
            if(updateItems) {
                mView?.setData(t)
            }

            for (item in t) {
               if(item.dislike || item.like){
                   rating++
               }
            }

            mView?.updateRatings(rating,total)
            if(rating == total){
                mView?.enableReviewButton()
            }else{
                mView?.disableReviewButton()
            }

        }

        override fun onComplete() {
        }

    }


    inner class ArticlesStateObserver : DisposableObserver<Boolean>() {
        override fun onError(e: Throwable) {
        }

        override fun onNext(t: Boolean) {
        }

        override fun onComplete() {
        }

    }
}