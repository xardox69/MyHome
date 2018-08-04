package com.myhome.app.di.module

import com.myhome.app.data.IAppRepository
import com.myhome.app.domain.usecases.GetArticles
import com.myhome.app.domain.usecases.UpdateArticle
import com.myhome.app.itemslist.ArticlePagerPresenter
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import javax.inject.Named
import javax.inject.Singleton


@Module
class ArticleListModule {

    @Provides
    @Singleton
    fun provideArticlePagerPresenter(getArticles: GetArticles, updateArticle: UpdateArticle,
                                     @Named(AppModule.SubscriberScheduler) subscriberScheduler: Scheduler,
                                     @Named(AppModule.ObserverScheduler) observerScheduler: Scheduler): ArticlePagerPresenter = ArticlePagerPresenter(getArticles, updateArticle, subscriberScheduler, observerScheduler)


    fun provideGetArtclesUsecase(repository: IAppRepository): GetArticles {
        return GetArticles(repository);
    }

    fun provideUpdateArtclesUsecase(repository: IAppRepository): UpdateArticle {
        return UpdateArticle(repository);
    }


}

