package com.myhome.app

import android.content.Context
import com.myhome.app.data.AppRepository
import com.myhome.app.data.local.LocalDataSource
import com.myhome.app.data.local.room.MyDatabase
import com.myhome.app.data.remote.RemoteDataSource
import com.myhome.app.domain.usecases.GetArticles
import com.myhome.app.domain.usecases.UpdateArticle
import com.myhome.app.itemslist.ArticlePagerPresenter
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object Injection {

   lateinit var context : Context

    fun provideUserListPresenter(mContext: Context): ArticlePagerPresenter{
            context = mContext
        return ArticlePagerPresenter(provideGetArticleUsecase(),provideUpdateArticle())

    }

    /*fun provideUserDetailPresenter(mContext: Context): UserDetailPresenter{
        context = mContext
        return UserDetailPresenter(provideUserDetailUsecase())
    }*/


    fun provideUpdateArticle() : UpdateArticle {
            return UpdateArticle(provideRepository());
    }

    fun provideGetArticleUsecase() : GetArticles {
        return GetArticles(provideRepository());
    }

    fun provideRepository(): AppRepository {
        return AppRepository(provideRemoteDataSource(),provideLocalDataSource())
    }

    fun provideDatabase() : MyDatabase{
        return MyDatabase.getInstance(context)
    }

    fun provideLocalDataSource(): LocalDataSource{
        return LocalDataSource.getInstance(provideDatabase().taskDao())
    }

    fun provideRemoteDataSource(): RemoteDataSource{
        return RemoteDataSource.getInstance(provideSubscriberScheduler(), provideObserverScheduler())
    }

    fun provideObserverScheduler(): Scheduler{
        return AndroidSchedulers.mainThread();
    }

    fun provideSubscriberScheduler(): Scheduler{
        return Schedulers.newThread();
    }
}