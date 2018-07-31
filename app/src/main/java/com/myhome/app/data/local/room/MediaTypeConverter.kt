package com.myhome.app.data.local.room

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.myhome.app.data.model.Article
import com.myhome.app.data.model.ArticleMedia

class MediaTypeConverter {

    @TypeConverter
    fun fromString(value: String): MutableList<ArticleMedia> {
        val type = object : TypeToken<ArticleMedia>() {
        }.type
        return Gson().fromJson<MutableList<ArticleMedia>>(value, type)
    }

    @TypeConverter
    fun fromArticleMedia(article: MutableList<ArticleMedia>): String {
        val gSon = Gson()
        return gSon.toJson(article)
    }

}