package com.myhome.app.itemslist

import android.content.Context

interface ArticleView {

    fun setTitle(title: String)

    fun setImage(url: String, context: Context)

    fun setliked()

    fun setDisliked()

    fun setViewTag(position: Int, sku: String)


}