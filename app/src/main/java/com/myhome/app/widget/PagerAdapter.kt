package com.myhome.app.widget

import android.content.Context
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup;
import com.myhome.app.R
import com.myhome.app.data.model.Article

class PagerAdapter constructor(private val list: MutableList<Article>) : PagerAdapter() {


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as View
    }


    override fun instantiateItem(parent: ViewGroup, position: Int): Any{
        val view = LayoutInflater.from(parent?.context)
                .inflate(R.layout.frag_articles_pager,parent,false)



        return view
    }


    override fun getCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container?.context)
                .inflate(R.layout.frag_articles_pager,container,false)

        return view
    }
}