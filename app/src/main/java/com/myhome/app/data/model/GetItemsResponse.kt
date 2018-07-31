package com.myhome.app.data.model

import android.os.Parcel
import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown=true)
data class GetItemsResponse(


        @JsonProperty("articles")
        var articles : MutableList<Article>):Parcelable{
    constructor(parcel: Parcel) : this(arrayListOf<Article>().apply {
        parcel.readList(this, Article::class.java.classLoader)
    })


    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GetItemsResponse> {
        override fun createFromParcel(parcel: Parcel): GetItemsResponse {
            return GetItemsResponse(parcel)
        }

        override fun newArray(size: Int): Array<GetItemsResponse?> {
            return arrayOfNulls(size)
        }
    }

}