package com.myhome.app.utils


import android.support.annotation.NonNull
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

object ActivityUtils{
    fun addFragmentToActivity(@NonNull fragmentManager: FragmentManager,
                              @NonNull fragment: Fragment,tag:String ,frameId: Int) {
        checkNotNull(fragmentManager)
        checkNotNull(fragment)
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(frameId, fragment).addToBackStack(tag).commit()
    }
}