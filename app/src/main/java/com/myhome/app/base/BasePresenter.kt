package com.myhome.app.base


interface BasePresenter<T>{

    fun takeView(view: T)

    fun dropView();

}


