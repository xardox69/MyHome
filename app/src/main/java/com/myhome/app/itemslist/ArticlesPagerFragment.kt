package com.myhome.app.itemslist

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.myhome.app.MyApp
import com.myhome.app.R
import com.myhome.app.data.model.Article
import com.myhome.app.reviewscreen.ReviewFragment
import com.myhome.app.utils.ActivityUtils
import com.myhome.app.utils.ViewUtils
import com.myhome.app.widget.MyPagerAdapter
import kotlinx.android.synthetic.main.frag_articles_pager.view.*
import javax.inject.Inject


class ArticlesPagerFragment : Fragment(), ArticlePagerContract.View, View.OnClickListener, ViewPager.OnPageChangeListener {




    // Adapter to set articles in a pager
    private lateinit var adapter: MyPagerAdapter


    companion object {
        const val TAG = "ArticlesPagerFragment"
        const val PAGER_KEY = "page_item"

        fun newInstance(): ArticlesPagerFragment {
            val articlesPagerFragment = ArticlesPagerFragment()
            val bundle = Bundle()
            articlesPagerFragment.arguments = bundle
            return articlesPagerFragment
        }
    }

    // viewpager to show items
    private lateinit var pager: ViewPager

    //presenter
    @Inject lateinit var presenter: ArticlePagerPresenter

    // textview to show total number of items
    private lateinit var total: TextView

    // textview to show the items rated so far
    private lateinit var ratedCount: TextView

    // button to be enabled after every item is reviewed
    private lateinit var reviewBtn: Button

    // snackbar to display the last item info
    private lateinit var snackbar: Snackbar

    // variable to set  position of the pager
    private var currentPosition: Int = 0


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.frag_articles_pager, container, false)
        (activity?.application as MyApp).appComponent.inject(this)
        pager = view.view_pager
        total = view.total
        ratedCount = view.rated
        reviewBtn = view.review_btn
        reviewBtn.setOnClickListener(this)
        adapter = MyPagerAdapter(arrayListOf<Article>(), this)
        pager.adapter = adapter
        presenter.getArticles()
        snackbar = Snackbar.make(activity!!.findViewById(android.R.id.content), "", Snackbar.LENGTH_SHORT)

        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt(PAGER_KEY)
        }

        return view
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

        //save pager state
        outState.putInt(PAGER_KEY, pager.currentItem)
    }

    override fun enableReviewButton() {
        reviewBtn.isEnabled = true
    }

    override fun disableReviewButton() {
        if (reviewBtn.isEnabled) {
            reviewBtn.isEnabled = false
        }
    }

    override fun updateRatings(rated: Int, total: Int) {
        ratedCount.text = rated.toString()
        this.total.text = total.toString()

    }

    override fun onClick(view: View?) {


        if(view?.id == R.id.like_image || view?.id == R.id.unlike_image ) {
            val sku: String = view.getTag(R.id.item_sku) as String
            val tempView = pager.findViewWithTag<View>(sku)

            //like button click
            if (view.id == R.id.like_image) {
                this.presenter.likeArticle(sku)
                ViewUtils.deselectDisLikeView(tempView)

                //dislike button click
            } else if (view.id == R.id.unlike_image) {
                this.presenter.dislikeArticle(sku)
                ViewUtils.deselectLikeView(tempView)
            }

            //move to next page after rating
            presenter.setNextpage(pager.currentItem, adapter.count)

        } else if (view?.id == R.id.review_btn && reviewBtn.isEnabled) {
            ActivityUtils.addFragmentToActivity(activity?.supportFragmentManager!!, ReviewFragment.newInstance()
                    , ReviewFragment.TAG, R.id.content)
        }

        presenter.updateRatings()
    }


    override fun setData(data: MutableList<Article>) {
        adapter.updateItems(data)
        pager.setCurrentItem(currentPosition, true)
    }

    override fun onDataLoaded() {
        if (snackbar.isShown) {
            snackbar.dismiss()
        }
    }

    override fun showLoading() {
        snackbar.setText(getString(R.string.lbl_loading_data))
        snackbar.show()
    }


    override fun showNextPage(page: Int) {
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
        presenter.pageChanged(position, adapter.count)
    }

    override fun showNetworkError() {
        snackbar.setText(getString(R.string.lbl_network_fail))
        snackbar.show()
    }


}


