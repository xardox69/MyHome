package com.myhome.app.di.articlelist

import com.aliumujib.githubtrending.di.app.scopes.FragmentScope
import com.myhome.app.data.IAppRepository
import com.myhome.app.di.app.module.AppModule
import com.myhome.app.domain.mapper.ArticleMapper
import com.myhome.app.domain.usecases.GetArticles
import com.myhome.app.domain.usecases.UpdateArticle
import com.myhome.app.itemslist.ArticlePagerContract
import com.myhome.app.itemslist.ArticlePagerPresenter
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import javax.inject.Named


@Module
class ArticleListModule {

    @Provides
    @FragmentScope
    fun provideArticlePagerPresenter(getArticles: GetArticles, updateArticle: UpdateArticle,
                                     @Named(AppModule.SubscriberScheduler) subscriberScheduler: Scheduler,
                                     @Named(AppModule.ObserverScheduler) observerScheduler: Scheduler): ArticlePagerContract.Presenter
            = ArticlePagerPresenter(getArticles, updateArticle, subscriberScheduler, observerScheduler)


    fun provideGetArtclesUsecase(repository: IAppRepository, mapper: ArticleMapper): GetArticles =
        GetArticles(repository,mapper)


    fun provideUpdateArtclesUsecase(repository: IAppRepository): UpdateArticle =
        UpdateArticle(repository)



}

