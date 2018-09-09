package com.myhome.app.di.app.module

import android.arch.persistence.room.Room
import android.content.Context
import com.myhome.app.data.local.ILocalDataSource
import com.myhome.app.data.local.LocalDataSource
import com.myhome.app.data.local.room.MyDatabase
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import javax.inject.Named
import javax.inject.Singleton

@Module
class LocalSourceModule{

    @Provides
    @Singleton
    fun provideLocalDataSource(database: MyDatabase, @Named(AppModule.SubscriberScheduler) subscriberScheduler: Scheduler,
                               @Named(AppModule.ObserverScheduler) observerScheduler: Scheduler): ILocalDataSource =
            LocalDataSource(database.taskDao(),subscriberScheduler,observerScheduler)


    @Provides
    @Singleton
    fun provideDatabase(@Named(AppModule.AppContext)context:Context): MyDatabase =
            Room.inMemoryDatabaseBuilder(context,
                    MyDatabase::class.java).allowMainThreadQueries()
                    .build()

}