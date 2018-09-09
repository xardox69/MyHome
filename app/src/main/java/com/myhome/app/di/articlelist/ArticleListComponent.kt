package com.myhome.app.di.articlelist

import com.aliumujib.githubtrending.di.app.scopes.FragmentScope
import com.myhome.app.di.app.AppComponent
import com.myhome.app.di.articlelist.ArticleListModule
import com.myhome.app.itemslist.ArticlesPagerFragment
import dagger.Component

@FragmentScope
@Component(dependencies = [(AppComponent::class)], modules = [(ArticleListModule::class)])
interface ArticleListComponent{

    fun inject(fragment: ArticlesPagerFragment)
}