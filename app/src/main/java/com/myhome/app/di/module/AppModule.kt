package com.myhome.app.di.module

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.myhome.app.data.AppRepository
import com.myhome.app.data.IAppRepository
import com.myhome.app.data.local.ILocalDataSource
import com.myhome.app.data.local.LocalDataSource
import com.myhome.app.data.local.room.MyDatabase
import com.myhome.app.data.remote.IRemoteDataSource
import com.myhome.app.data.remote.RemoteDataSource
import com.myhome.app.domain.mapper.ArticleMapper
import com.myhome.app.domain.mapper.ArticleMediaMapper
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule(private val app: Application) {

    companion object {
        const val SubscriberScheduler = "subscriber_scheduler"
        const val ObserverScheduler = "observer_scheduler"
    }


    @Provides
    @Singleton
    fun provideContext(): Context = app


    @Provides
    @Named(SubscriberScheduler)
    fun provideSubscriberScheduler(): Scheduler = Schedulers.newThread()


    @Provides
    @Named(ObserverScheduler)
    fun provideObserverScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    @Singleton
    fun provideRepository(localDataSource: ILocalDataSource, remoteDataSource: IRemoteDataSource): IAppRepository =
         AppRepository(remoteDataSource, localDataSource)


    @Provides
    @Singleton
    fun provideLocalDataSource(database: MyDatabase,@Named(SubscriberScheduler) subscriberScheduler: Scheduler,
                               @Named(ObserverScheduler) observerScheduler: Scheduler): ILocalDataSource =
         LocalDataSource(database.taskDao(),subscriberScheduler,observerScheduler)


    @Provides
    @Singleton
    fun provideRemoteDataSource(@Named(SubscriberScheduler) subscriberScheduler: Scheduler,
                                @Named(ObserverScheduler) observerScheduler: Scheduler): IRemoteDataSource =
         RemoteDataSource(subscriberScheduler, observerScheduler)


    @Provides
    @Singleton
    fun provideDatabase(): MyDatabase =
         Room.inMemoryDatabaseBuilder(app,
                MyDatabase::class.java).allowMainThreadQueries()
                .build()

    @Provides
    @Singleton
    fun provideArticleMapper(mediaMapper: ArticleMediaMapper): ArticleMapper = ArticleMapper(mediaMapper)


    @Provides
    @Singleton
    fun provideMediaMapper():ArticleMediaMapper = ArticleMediaMapper()

}




