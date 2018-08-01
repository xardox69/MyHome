package com.myhome.app.utils

import android.view.View
import android.widget.ImageView
import com.myhome.app.R

object ViewUtils {

    fun deselectLikeView(view : View){
        view.findViewById<ImageView>(R.id.like_image).isSelected = false
    }


    fun deselectDisLikeView(view : View){
        view.findViewById<ImageView>(R.id.unlike_image).isSelected = false
    }
}