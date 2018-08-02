package com.myhome.app.itemslist

import android.os.Bundle
import com.myhome.app.base.BaseView
import com.myhome.app.data.model.Article

interface ArticlePagerContract {


    interface View : BaseView {

        fun setData(data: MutableList<Article>)

        fun onDataLoaded()

        fun showLoading()

        fun updateRatings(rated: Int, total: Int)

        fun enableReviewButton()

        fun disableReviewButton()

        fun showNextPage(page: Int)

        fun showNoItemsLeft()

        fun showLastItem()

    }


    interface Presenter {


        fun takeView(view: ArticlePagerContract.View)

        fun dropView()

        fun getArticles()

        fun likeArticle(sku: String)

        fun dislikeArticle(sku: String)

        fun updateRatings();

        fun setNextpage(currentPage: Int, totalPages: Int)

        fun pageChanged(currentPage: Int, totalPages: Int)

    }


}