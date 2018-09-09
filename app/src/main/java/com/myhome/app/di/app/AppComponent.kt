package com.myhome.app.di.app

import com.myhome.app.MyApp
import com.myhome.app.data.IAppRepository
import com.myhome.app.di.app.module.AppModule
import com.myhome.app.di.app.module.LocalSourceModule
import com.myhome.app.di.app.module.RemoteSourceModule
import com.myhome.app.domain.mapper.ArticleMediaMapper
import dagger.Component
import io.reactivex.Scheduler
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, LocalSourceModule::class, RemoteSourceModule::class])
interface AppComponent{
    fun inject(target: MyApp)

    fun getRepo(): IAppRepository

    fun getMediaMapper() : ArticleMediaMapper

    @Named(AppModule.ObserverScheduler)
    fun provideObserverScheduler(): Scheduler

    @Named(AppModule.SubscriberScheduler)
    fun provideSubscriberScheduler(): Scheduler

}