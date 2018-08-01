package com.myhome.app.reviewscreen

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SwitchCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Switch
import com.myhome.app.Injection
import com.myhome.app.R
import com.myhome.app.data.model.Article
import com.myhome.app.itemslist.ArticlesPagerFragment
import com.myhome.app.utils.ListState
import com.myhome.app.widget.MyPagerAdapter
import com.myhome.app.widget.ReviewAdapter
import kotlinx.android.synthetic.main.review_ffragment.view.*

class ReviewFragment:Fragment() , ReviewContract.View, CompoundButton.OnCheckedChangeListener {
    override fun onCheckedChanged(p0: CompoundButton?, isChecked: Boolean) {

        if(isChecked){

            switch.setText("Grid")
            recyclerView.layoutManager = linearLayoutManager
            myAdapter.type = ListState.VERTICAL.value
        }else{
            switch.setText("List")
            recyclerView.layoutManager = gridLayoutManager
            myAdapter.type = ListState.HORIZONTAL.value


        }
        recyclerView.invalidate()

    }


    override fun setData(items: MutableList<Article>) {
        myAdapter.updateItems(items)
    }

    companion object {
        val TAG = "ArticlesPagerFragment"

        fun newInstance(): ReviewFragment {
            var reviewFragment = ReviewFragment()
            var bundle = Bundle()
            reviewFragment.arguments = bundle
            return reviewFragment
        }
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var myAdapter : ReviewAdapter
    private lateinit var presenter: ReviewPresenter
    private lateinit var switch: Switch


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.review_ffragment, container, false)
        recyclerView = view.recycler
        switch = view.simpleSwitch
        switch.setOnCheckedChangeListener(this)

        linearLayoutManager = LinearLayoutManager(inflater.context)
        gridLayoutManager = GridLayoutManager(inflater.context,2)
        recyclerView.layoutManager = gridLayoutManager
        myAdapter = ReviewAdapter(inflater.context,arrayListOf<Article>(),ListState.HORIZONTAL.value)
        recyclerView.adapter = myAdapter
        presenter = Injection.provideReviewPresenter(inflater.context.applicationContext)
        presenter.getArticles()
        return view
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