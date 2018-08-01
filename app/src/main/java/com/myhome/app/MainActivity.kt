package com.myhome.app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.myhome.app.itemslist.ArticlesPagerFragment
import com.myhome.app.reviewscreen.ReviewFragment
import com.myhome.app.utils.ActivityUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        if(supportFragmentManager.backStackEntryCount > 0){
            start_btn.visibility = View.GONE
        }
    }


    fun onStartClicked(view : View){
        ActivityUtils.addFragmentToActivity(supportFragmentManager, ArticlesPagerFragment.newInstance(),ArticlesPagerFragment.TAG,
                R.id.content)
        view.visibility = View.GONE
    }


    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount == 1){
            supportFragmentManager.popBackStack()
        }
        super.onBackPressed()
    }
}
