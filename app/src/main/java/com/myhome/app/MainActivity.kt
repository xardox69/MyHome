package com.myhome.app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.myhome.app.itemslist.ArticlesPagerFragment
import com.myhome.app.utils.ActivityUtils

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun onStartClicked(view : View){
        ActivityUtils.addFragmentToActivity(supportFragmentManager, ArticlesPagerFragment.newInstance(), R.id.content)
        view.visibility = View.GONE
    }


    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount == 1){
            supportFragmentManager.popBackStack()
        }
        super.onBackPressed()
    }
}
