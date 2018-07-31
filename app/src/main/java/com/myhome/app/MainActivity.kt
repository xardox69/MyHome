package com.myhome.app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.myhome.app.itemslist.ArticlesPagerFragment
import com.myhome.app.utils.ActivityUtils

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if(savedInstanceState == null){
            ActivityUtils.addFragmentToActivity(supportFragmentManager,ArticlesPagerFragment.newInstance(),R.id.content)
        }
    }
}
