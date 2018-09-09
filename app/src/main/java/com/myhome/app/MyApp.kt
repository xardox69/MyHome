package com.myhome.app

import android.app.Application
import com.myhome.app.di.app.AppComponent
import com.myhome.app.di.component.DaggerAppComponent
import com.myhome.app.di.app.module.AppModule

class MyApp:Application() {

    lateinit var appComponent: AppComponent


    override fun onCreate() {
        super.onCreate()
        appComponent = initDagger(this)
    }




    private fun initDagger(app: MyApp): AppComponent =
            DaggerAppComponent.builder()
                    .appModule(AppModule(app))
                    .build()
}