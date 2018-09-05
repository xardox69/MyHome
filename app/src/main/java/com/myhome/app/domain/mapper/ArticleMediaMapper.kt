package com.myhome.app.domain.mapper

import com.myhome.app.Mapper
import com.myhome.app.data.model.ArticleMedia
import com.myhome.app.domain.entities.ArticleMediaModel

class ArticleMediaMapper : Mapper<ArticleMedia, ArticleMediaModel>() {
    override fun mapFrom(from: ArticleMedia): ArticleMediaModel = ArticleMediaModel(from.uri)


}