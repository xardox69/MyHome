package com.myhome.app.domain.entities

import com.myhome.app.data.model.ArticleMedia

class ArticleModel constructor(val sku:String,val title: String,var like: Boolean,
                               var dislike: Boolean,val media: MutableList<ArticleMediaModel>) {

}