package com.myhome.app.itemslist

import android.os.Bundle
import com.myhome.app.base.BaseView
import com.myhome.app.data.model.Article

interface ArticlePagerContract{



    interface View : BaseView {

        fun setData(data: MutableList<Article>)

        fun onDataLoaded()

        fun showLoading()

    }


    interface Presenter {



        fun takeView(view: ArticlePagerContract.View)

        fun dropView()

        fun saveState(outState: Bundle)

        fun getCachedState(savedInstanceState: Bundle)

        fun getCachedArticles()

        fun getArticles()

        fun likeArticle(sku: String)

        fun dislikeArticle(sku:String)

    }












}