package com.myhome.app.itemslist

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.myhome.app.Injection
import com.myhome.app.R
import kotlinx.android.synthetic.main.frag_articles_pager.view.*


class ArticlesPagerFragment : Fragment(){



    companion object {
        val TAG = "ArticlesPagerFragment"

        fun newInstance(): ArticlesPagerFragment {
            var articlesPagerFragment = ArticlesPagerFragment()
            var bundle = Bundle()
            articlesPagerFragment.arguments = bundle
            return articlesPagerFragment
        }
    }


    lateinit var pager:ViewPager
    lateinit var presenter: ArticlePagerPresenter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.frag_articles_pager, container, false)
        pager = view.view_pager
        presenter = Injection.provideUserListPresenter(context?.applicationContext!!)
        presenter.getArticles()


        return view;
    }




}


