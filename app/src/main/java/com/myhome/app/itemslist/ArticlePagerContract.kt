package com.myhome.app.itemslist

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



    }


    interface Presenter {

        /**
         * Subsribe to  view
         */
        fun takeView(view: ArticlePagerContract.View)

        /**
         * Unsubscribe from a view
         */
        fun dropView()

        /**
         * Gets the data from repository
         */
        fun getArticles()

        /**
         * Called when an item is positivly rated
         */
        fun likeArticle(sku: String)

        fun dislikeArticle(sku: String)

        fun updateRatings()

        fun setNextpage(currentPage: Int, totalPages: Int)

        fun pageChanged(currentPage: Int, totalPages: Int)

    }


}