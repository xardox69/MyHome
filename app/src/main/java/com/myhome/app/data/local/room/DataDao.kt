package com.myhome.app.data.local.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.myhome.app.data.model.Article


@Dao
interface DataDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(item: Article)


    @Query("SELECT * FROM article WHERE sku = :givenSKU")
    fun getArticleWithSKU(givenSKU:String): Article

    @Query("SELECT * FROM article")
    fun getArticles(): MutableList<Article>

    @Query("UPDATE article SET love = :likeState , dislike = :dislikeState  WHERE sku = :givenSKU")
    fun likeArticle(likeState: Boolean,givenSKU:String,dislikeState: Boolean )




}