package com.myhome.app.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.ColumnInfo
import android.support.annotation.NonNull
import android.os.Parcel
import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown=true)
@Entity(tableName = "article")
data class Article(


        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "sku")
        @JsonProperty("sku")
        var sku:String,


        @ColumnInfo(name = "title")
        @JsonProperty("title")
        var title:String,



        @ColumnInfo(name = "description")
        @JsonProperty("description")
        var description:String,


        @ColumnInfo(name = "love")
        var like:Boolean,


        @ColumnInfo(name = "dislike")
        var dislike:Boolean,



        @ColumnInfo(name = "media")
        @JsonProperty("media")
        var media: MutableList<ArticleMedia>) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readByte() != 0.toByte(),
            parcel.readByte() != 0.toByte(),
            TODO("media")) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(sku)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeByte(if (like) 1 else 0)
        parcel.writeByte(if (dislike) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Article> {
        override fun createFromParcel(parcel: Parcel): Article {
            return Article(parcel)
        }

        override fun newArray(size: Int): Array<Article?> {
            return arrayOfNulls(size)
        }
    }

}