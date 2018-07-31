package com.myhome.app.itemslist

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.myhome.app.R
import kotlinx.android.synthetic.main.frag_articles_pager.view.*


class ArticlesPagerFragment : Fragment(){

    companion object {
        val TAG = "UserListFragment"

        fun newInstance(): ArticlesPagerFragment {
            var articlesPagerFragment = ArticlesPagerFragment()
            var bundle = Bundle()
            articlesPagerFragment.arguments = bundle
            return articlesPagerFragment
        }
    }


    lateinit var pager:ViewPager


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.frag_articles_pager, container, false)
        pager = view.view_pager

        return view;
    }




}


