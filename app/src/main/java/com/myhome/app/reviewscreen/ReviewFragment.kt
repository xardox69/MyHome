package com.myhome.app.reviewscreen

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Switch
import com.myhome.app.MyApp
import com.myhome.app.R
import com.myhome.app.data.model.Article
import com.myhome.app.utils.ListState
import com.myhome.app.widget.ReviewAdapter
import kotlinx.android.synthetic.main.review_ffragment.view.*
import javax.inject.Inject

class ReviewFragment : Fragment(), ReviewContract.View, CompoundButton.OnCheckedChangeListener {


    override fun setData(items: MutableList<Article>) {
        myAdapter.updateItems(items)
    }

    companion object {
        const val TAG = "ArticlesPagerFragment"

        fun newInstance(): ReviewFragment {
            val reviewFragment = ReviewFragment()
            val bundle = Bundle()
            reviewFragment.arguments = bundle
            return reviewFragment
        }
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var myAdapter: ReviewAdapter
    @Inject lateinit var presenter: ReviewPresenter
    private lateinit var switch: Switch
    private lateinit var snackbar: Snackbar


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.review_ffragment, container, false)
        (activity?.application as MyApp).appComponent.inject(this)
        recyclerView = view.recycler
        switch = view.simpleSwitch
        switch.setOnCheckedChangeListener(this)

        linearLayoutManager = LinearLayoutManager(inflater.context)
        gridLayoutManager = GridLayoutManager(inflater.context, 2)
        snackbar = Snackbar.make(activity!!.findViewById(android.R.id.content), "", Snackbar.LENGTH_SHORT)
        recyclerView.layoutManager = gridLayoutManager
        myAdapter = ReviewAdapter(inflater.context, arrayListOf<Article>(), ListState.HORIZONTAL.value)
        recyclerView.adapter = myAdapter


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

    override fun onCheckedChanged(p0: CompoundButton?, isChecked: Boolean) {

        if (isChecked) {

            switch.text = getString(R.string.lbl_grid)
            recyclerView.layoutManager = linearLayoutManager
            myAdapter.type = ListState.VERTICAL.value
        } else {
            switch.text = getString(R.string.lbl_list)
            recyclerView.layoutManager = gridLayoutManager
            myAdapter.type = ListState.HORIZONTAL.value


        }
        recyclerView.invalidate()

    }

    override fun showLoading() {
        snackbar.setText(getString(R.string.lbl_loading_data))
        snackbar.show()
    }

    override fun dismissLoading() {
        if (snackbar.isShown) {
            snackbar.dismiss()
        }
    }


}