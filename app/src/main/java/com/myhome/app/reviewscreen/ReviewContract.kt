package com.myhome.app.reviewscreen

import com.myhome.app.base.BaseView
import com.myhome.app.data.model.Article

interface ReviewContract {

    interface View : BaseView {

        fun setData(items :MutableList<Article>)

    }

    interface Presenter {
        fun takeView(view: ReviewContract.View)
        fun dropView()
        fun getArticles()

    }


}