package com.myhome.app.di.review

import com.aliumujib.githubtrending.di.app.scopes.FragmentScope
import com.myhome.app.di.app.AppComponent
import com.myhome.app.reviewscreen.ReviewFragment
import dagger.Component

@FragmentScope
@Component(dependencies = [(AppComponent::class)], modules = [(ReviewModule::class)])
interface ReviewComponent{

    fun inject(fragment: ReviewFragment)
}