package com.myhome.app

import com.myhome.app.data.AppRepository
import com.myhome.app.data.IAppRepository
import com.myhome.app.data.local.LocalDataSource
import com.myhome.app.data.local.room.DataDao
import com.myhome.app.data.model.Article
import com.myhome.app.data.model.ArticleMedia
import com.myhome.app.data.remote.RemoteDataSource
import com.myhome.app.domain.Params
import com.myhome.app.domain.usecases.GetArticles
import com.myhome.app.domain.usecases.UpdateArticle
import com.myhome.app.itemslist.ArticlePagerContract
import com.myhome.app.itemslist.ArticlePagerPresenter

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

import org.mockito.Matchers.any
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class ListItemPresenterTest {


    //private lateinit var appRepository: AppRepository

    @Mock
    lateinit var dao: DataDao

     lateinit var remoteDataSource: RemoteDataSource
     lateinit var localDataSource: LocalDataSource

    @Mock private lateinit var pagerView: ArticlePagerContract.View

    private lateinit var presenter :ArticlePagerPresenter

    private lateinit var articles: MutableList<Article>

    @Before
    fun  setupPresenter() {
        MockitoAnnotations.initMocks(this);

        remoteDataSource  = RemoteDataSource(Schedulers.trampoline(),Schedulers.trampoline())
        localDataSource = LocalDataSource(dao)

        var appRepository: AppRepository = AppRepository(remoteDataSource, localDataSource)

        var getArticles :GetArticles = GetArticles(appRepository)
        var updateArticle: UpdateArticle = UpdateArticle(appRepository)

        presenter = ArticlePagerPresenter(getArticles,updateArticle,Schedulers.trampoline()  ,Schedulers.trampoline() )

        articles = arrayListOf<Article>()

        var article :Article = Article("000","asasd",true,false, arrayListOf<ArticleMedia>())

        articles.add(article)


        `when`(dao.getArticles()).thenReturn(articles)

    }


    @Test fun get_Data_from_presenter_show_loading(){
        presenter.takeView(pagerView)
        presenter.getArticles()
        verify(pagerView).showLoading()
    }

    @Test fun get_Data_from_presenter_show_data(){
        presenter.takeView(pagerView)
        presenter.getArticles()
        verify(pagerView).showLoading()
        verify(pagerView).setData(articles)
    }


    @Test fun get_Data_from_presenter_dismiss_loading(){
        presenter.takeView(pagerView)
        presenter.getArticles()
        verify(pagerView).showLoading()
        verify(pagerView).setData(articles)
        verify(pagerView).onDataLoaded()
    }



}