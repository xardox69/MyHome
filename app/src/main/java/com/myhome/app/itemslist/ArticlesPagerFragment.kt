package com.myhome.app.itemslist

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.myhome.app.Injection
import com.myhome.app.R
import com.myhome.app.data.model.Article
import com.myhome.app.widget.MyPagerAdapter
import kotlinx.android.synthetic.main.frag_articles_pager.view.*


class ArticlesPagerFragment : Fragment(), ArticlePagerContract.View, View.OnClickListener {
    override fun onClick(p0: View?) {
        val sku :String = p0?.getTag().toString()
        if(p0?.id == R.id.like_image){
            presenter.likeArticle(sku)
        }else if(p0?.id == R.id.unlike_image){
            presenter.dislikeArticle(sku)
        }
    }


    lateinit var adapter: MyPagerAdapter


    override fun setData(data: MutableList<Article>) {
        adapter.updateItems(data)
    }

    override fun onDataLoaded() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


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
        adapter = MyPagerAdapter(arrayListOf<Article>(),this)
        pager.adapter = adapter
        presenter = Injection.provideUserListPresenter(context?.applicationContext!!)
        presenter.getArticles()

        return view;
    }

    override fun onResume() {
        super.onResume()
        presenter.takeView(this)
    }


    override fun onPause() {
        super.onPause()
        presenter.dropView()
    }




}


