package com.myhome.app.di.component

import com.myhome.app.di.module.AppModule
import com.myhome.app.di.module.ArticleListModule
import com.myhome.app.di.module.ReviewModule
import com.myhome.app.itemslist.ArticlesPagerFragment
import com.myhome.app.reviewscreen.ReviewFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ArticleListModule::class, ReviewModule::class])
interface AppComponent{
    fun inject(target: ReviewFragment)
    fun inject(target: ArticlesPagerFragment)
}