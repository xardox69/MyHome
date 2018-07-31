package com.myhome.app.data.local

import com.myhome.app.data.model.Article
import io.reactivex.Observable

interface ILocalDataSource{


  fun saveItem(item: Article)

  fun saveItems(items: MutableList<Article>)

  fun getItems(sku:String):Observable<Article>

  fun getItems():Observable<MutableList<Article>>

  fun likeArticle( sku:String)

  fun dislikeArticle( sku:String)




}