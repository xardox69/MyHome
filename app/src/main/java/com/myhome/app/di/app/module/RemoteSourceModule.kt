package com.myhome.app.di.app.module

import com.myhome.app.data.remote.IRemoteDataSource
import com.myhome.app.data.remote.RemoteDataSource
import com.myhome.app.di.app.module.AppModule
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import javax.inject.Named
import javax.inject.Singleton


@Module
class RemoteSourceModule{

    @Provides
    @Singleton
    fun provideRemoteDataSource(@Named(AppModule.SubscriberScheduler) subscriberScheduler: Scheduler,
                                @Named(AppModule.ObserverScheduler) observerScheduler: Scheduler): IRemoteDataSource =
            RemoteDataSource(subscriberScheduler, observerScheduler)

}