package com.myhome.app.domain.mapper

import com.myhome.app.Mapper
import com.myhome.app.data.model.Article
import com.myhome.app.domain.entities.ArticleModel
import javax.inject.Inject

class ArticleMapper @Inject constructor(private val mediaMapper: ArticleMediaMapper) : Mapper<Article, ArticleModel>() {


    override fun mapFrom(from: Article): ArticleModel = ArticleModel(from.sku, from.title, from.like,
            from.dislike, mediaMapper.mapFrom(from.media))


}