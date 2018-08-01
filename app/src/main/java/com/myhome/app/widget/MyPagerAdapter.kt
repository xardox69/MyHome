package com.myhome.app.widget

import android.content.Context
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup;
import com.myhome.app.R
import com.myhome.app.data.model.Article
import com.myhome.app.itemslist.ArticleViewHolder
import kotlinx.android.synthetic.main.page_layout.view.*

class MyPagerAdapter constructor(private val list: MutableList<Article>, private val clickListener :View.OnClickListener) : PagerAdapter(),View.OnClickListener {



    override fun onClick(view: View?) {


        val position: Int = view?.getTag(R.id.item_position) as Int
        val sku: String = view?.getTag(R.id.item_sku) as String

        if (view?.id == R.id.like_image) {

            if (view.isSelected) {
                view.isSelected = false
            } else {
                view.isSelected = true
            }
        }
    }


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as View
    }


    override fun getCount(): Int {
        return list.size
    }


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container?.context)
                .inflate(R.layout.page_layout,container,false)

         var item :Article = list.get(position)

        var holder :ArticleViewHolder = ArticleViewHolder(view)

        holder.setTitle(item.title)
        holder.setImage(item.media.get(0).uri,container?.context)

        if(item.like){
            holder.setliked()
        }else if(item.dislike){
            holder.setDisliked()
        }

        holder.setTag(position,item.sku)


        container?.addView(view)
        return view
    }


    override fun destroyItem(parent: ViewGroup, position: Int, `object`: Any) {
        // Remove the view from view group specified position
        parent.removeView(`object` as View)
    }

    fun updateItems(updates: MutableList<Article>){
        list.clear();
        list.addAll(updates)
        notifyDataSetChanged()
    }
}