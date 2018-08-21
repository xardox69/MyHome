package com.myhome.app.di.module

import com.myhome.app.data.IAppRepository
import com.myhome.app.domain.usecases.GetArticles
import com.myhome.app.reviewscreen.ReviewPresenter
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import javax.inject.Named
import javax.inject.Singleton


@Module
class ReviewModule{
    @Provides
    @Singleton
    fun provideReviewPresenter(getArticles: GetArticles,
                                 @Named(AppModule.SubscriberScheduler) subscriberScheduler: Scheduler,
                                 @Named(AppModule.ObserverScheduler)observerScheduler: Scheduler): ReviewPresenter
            = ReviewPresenter(getArticles,subscriberScheduler,observerScheduler)


    fun provideGetArtclesUsecase(repository: IAppRepository) : GetArticles =

         GetArticles(repository)

}