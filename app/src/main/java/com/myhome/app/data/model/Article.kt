package com.myhome.app.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.TypeConverters
import android.support.annotation.NonNull
import android.support.annotation.Nullable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.myhome.app.data.local.room.MediaTypeConverter


@JsonIgnoreProperties(ignoreUnknown = true)
@TypeConverters(MediaTypeConverter::class)
@Entity(tableName = "article")
data class Article(


        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "sku")
        @JsonProperty("sku")
        var sku: String,

        @Nullable
        @ColumnInfo(name = "title")
        @JsonProperty("title")
        var title: String,


        @Nullable
        @ColumnInfo(name = "love")
        @JsonProperty("love")
        var like: Boolean,

        @Nullable
        @ColumnInfo(name = "dislike")
        @JsonProperty("dislike")
        var dislike: Boolean,

        @Nullable
        @ColumnInfo(name = "media")
        @JsonProperty("media")
        var media: MutableList<ArticleMedia>) {
        override fun toString(): String {
                return "Article(sku='$sku', title='$title', like=$like, dislike=$dislike, media=$media)"
        }
}
