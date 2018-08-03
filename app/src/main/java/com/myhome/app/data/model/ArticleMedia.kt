package com.myhome.app.data.model

import android.arch.persistence.room.TypeConverters
import android.os.Parcel
import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public data class ArticleMedia(


        @JsonProperty("uri")
        var uri: String) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(uri)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "ArticleMedia(uri='$uri')"
    }

    companion object CREATOR : Parcelable.Creator<ArticleMedia> {
        override fun createFromParcel(parcel: Parcel): ArticleMedia {
            return ArticleMedia(parcel)
        }

        override fun newArray(size: Int): Array<ArticleMedia?> {
            return arrayOfNulls(size)
        }
    }

}