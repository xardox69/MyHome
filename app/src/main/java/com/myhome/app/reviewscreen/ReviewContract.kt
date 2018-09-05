package com.myhome.app.reviewscreen

import com.myhome.app.base.BaseView
import com.myhome.app.domain.entities.ArticleModel

interface ReviewContract {

    interface View : BaseView {

        fun setData(items :MutableList<ArticleModel>)

        fun showLoading()

        fun dismissLoading()

    }

    interface Presenter {
        fun takeView(view: ReviewContract.View)
        fun dropView()
        fun getArticles()

    }


}