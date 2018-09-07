package com.myhome.app.domain.entities


class ArticleModel constructor(val sku:String,val title: String,var like: Boolean,
                               var dislike: Boolean,val media: MutableList<ArticleMediaModel>) {

}