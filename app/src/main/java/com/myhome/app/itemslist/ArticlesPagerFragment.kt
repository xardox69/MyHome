package com.myhome.app.itemslist

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.myhome.app.Injection
import com.myhome.app.R
import com.myhome.app.data.model.Article
import com.myhome.app.reviewscreen.ReviewFragment
import com.myhome.app.utils.ActivityUtils
import com.myhome.app.utils.ViewUtils
import com.myhome.app.widget.MyPagerAdapter
import kotlinx.android.synthetic.main.frag_articles_pager.view.*



class ArticlesPagerFragment : Fragment(), ArticlePagerContract.View, View.OnClickListener, ViewPager.OnPageChangeListener {
    override fun showLastItem() {
    }


    private lateinit var adapter: MyPagerAdapter


    companion object {
        val TAG = "ArticlesPagerFragment"
        val PAGER_KEY = "page_item"

        fun newInstance(): ArticlesPagerFragment {
            var articlesPagerFragment = ArticlesPagerFragment()
            var bundle = Bundle()
            articlesPagerFragment.arguments = bundle
            return articlesPagerFragment
        }
    }


    private lateinit var pager:ViewPager
    private lateinit var presenter: ArticlePagerPresenter
    private lateinit var total :TextView
    private lateinit var ratedCount : TextView
    private lateinit var reviewBtn : Button
    private lateinit var snackbar: Snackbar
    private var currentPosition :Int = 0


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.frag_articles_pager, container, false)

        pager = view.view_pager
        total = view.total
        ratedCount = view.rated
        reviewBtn = view.review_btn
        reviewBtn.setOnClickListener(this)
        adapter = MyPagerAdapter(arrayListOf<Article>(),this)
        pager.adapter = adapter

        presenter = Injection.provideUserListPresenter(context?.applicationContext!!)
        presenter.getArticles()
        snackbar = Snackbar.make(activity!!.findViewById(android.R.id.content),"",Snackbar.LENGTH_SHORT)

        if(savedInstanceState!=null){
            currentPosition = savedInstanceState.getInt(PAGER_KEY)
        }

        return view;
    }

    override fun onResume() {
        super.onResume()
        pager.addOnPageChangeListener(this)
        presenter.takeView(this)
    }


    override fun onPause() {
        super.onPause()
        pager.removeOnPageChangeListener(this)
        presenter.dropView()
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(PAGER_KEY,pager.currentItem)
    }

    override fun enableReviewButton() {
        reviewBtn.isEnabled = true
    }

    override fun disableReviewButton(){
        if(reviewBtn.isEnabled){
            reviewBtn.isEnabled = false
        }
    }

    override fun updateRatings(rated: Int, totalNum: Int) {
        ratedCount.setText(rated.toString())
        total.setText(totalNum.toString())

    }

    override fun onClick(view: View?) {

        if(view?.id == R.id.like_image || view?.id == R.id.unlike_image ) {
            val sku: String = view?.getTag(R.id.item_sku) as String
            val tempView = pager.findViewWithTag<View>(sku)

            if (view?.id == R.id.like_image) {
                presenter.likeArticle(sku)
                ViewUtils.deselectDisLikeView(tempView)
            } else if (view?.id == R.id.unlike_image) {
                presenter.dislikeArticle(sku)
                ViewUtils.deselectLikeView(tempView)
            }
            presenter.setNextpage(pager.currentItem,adapter.count)

        }else if(view?.id == R.id.review_btn && reviewBtn.isEnabled){
        ActivityUtils.addFragmentToActivity(activity?.supportFragmentManager!!,ReviewFragment.newInstance()
                ,ReviewFragment.TAG,R.id.content)
        }

        presenter.updateRatings()
    }


    override fun setData(data: MutableList<Article>) {
        adapter.updateItems(data)
        pager.setCurrentItem(currentPosition,true)
    }

    override fun onDataLoaded() {
        if(snackbar.isShown){
            snackbar.dismiss()
        }
    }

    override fun showLoading() {
        snackbar.setText(getString(R.string.lbl_loading_data))
        snackbar.show()
    }


    override fun showNextPage(page:Int) {
        pager.currentItem = page
    }

    override fun showNoItemsLeft() {
        snackbar.setText(getString(R.string.lbl_last_item))
        snackbar.show()
    }



    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        presenter.pageChanged(position,adapter.count)
    }




}


