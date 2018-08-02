package com.myhome.app.itemslist

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.myhome.app.R
import com.myhome.app.widget.GlideApp
import kotlinx.android.synthetic.main.page_layout.view.*

class ArticleViewHolder constructor(var itemView: View) : ArticleView {


    override fun setViewTag(position: Int, sku: String) {
        likeImage.setTag(R.id.item_sku, sku)
        likeImage.setTag(R.id.item_position, position)
        dislikeImage.setTag(R.id.item_sku, sku)
        dislikeImage.setTag(R.id.item_position, position)
    }

    var title: TextView = itemView.title
    var image: ImageView = itemView.image
    var likeImage: ImageView = itemView.like_image
    var dislikeImage: ImageView = itemView.unlike_image

    override fun setTitle(sTitle: String) {
        title.text = sTitle
    }

    override fun setImage(url: String, context: Context) {
        GlideApp.with(context).load(url).into(image)
    }

    override fun setliked() {
        likeImage.isSelected = true
        dislikeImage.isSelected = false
    }

    override fun setDisliked() {
        likeImage.isSelected = false
        dislikeImage.isSelected = true
    }


}