package com.myhome.app

import com.myhome.app.data.AppRepository
import com.myhome.app.data.IAppRepository
import com.myhome.app.data.local.LocalDataSource
import com.myhome.app.data.local.room.DataDao
import com.myhome.app.data.model.Article
import com.myhome.app.data.model.ArticleMedia
import com.myhome.app.data.remote.RemoteDataSource
import com.myhome.app.domain.Params
import org.mockito.Captor;
import com.myhome.app.domain.entities.ArticleModel
import com.myhome.app.domain.mapper.ArticleMapper
import com.myhome.app.domain.mapper.ArticleMediaMapper
import com.myhome.app.domain.usecases.GetArticles
import com.myhome.app.domain.usecases.UpdateArticle
import com.myhome.app.itemslist.ArticlePagerContract
import com.myhome.app.itemslist.ArticlePagerPresenter
import de.jodamob.kotlin.testrunner.KotlinTestRunner

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import org.junit.runner.RunWith

import org.mockito.Matchers.any
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.ArgumentCaptor




@RunWith(KotlinTestRunner::class)
class ListItemPresenterTest {

    @Mock
    lateinit var appRepository: AppRepository

    //@Mock
    //lateinit var dao: DataDao


    //@Mock
    //lateinit var remoteDataSource: RemoteDataSource

    //@Mock
    //lateinit var localDataSource: LocalDataSource



    /*@Mock
    lateinit var getArticles :GetArticles*/




    /*@Mock
    lateinit var updateArticle: UpdateArticle*/

    @Mock private lateinit var pagerView: ArticlePagerContract.View

    private lateinit var presenter :ArticlePagerPresenter

    private lateinit var articles: MutableList<Article>

    @Before
    fun  setupPresenter() {
        MockitoAnnotations.initMocks(this);

     //  val  remoteDataSource  = RemoteDataSource(Schedulers.trampoline(),Schedulers.trampoline())
     //   val localDataSource = LocalDataSource(dao,Schedulers.trampoline(),Schedulers.trampoline())



        var getArticles  = GetArticles(appRepository, ArticleMapper(ArticleMediaMapper()))
        var updateArticle: UpdateArticle = UpdateArticle(appRepository)

        presenter = ArticlePagerPresenter(getArticles,updateArticle,Schedulers.trampoline()  ,Schedulers.trampoline() )

        articles = arrayListOf<Article>()

        var article :Article = Article("000","asasd",true,false, arrayListOf<ArticleMedia>())

        articles.add(article)




        `when`(appRepository.getItems(Params.create())).thenReturn(Observable.just(articles))
        `when`(appRepository.getCachedItems()).thenReturn(Observable.just(articles))

    }


    @Test fun get_Data_from_presenter_show_loading(){
        presenter.takeView(pagerView)
        presenter.getArticles()
        verify(pagerView).showLoading()
    }

    @Test fun get_Data_from_presenter_show_data(){
        val argument = ArgumentCaptor.forClass(List::class.java as Class<*>)
        presenter.takeView(pagerView)
        presenter.getArticles()
        verify(pagerView).showLoading()
        //verify(pagerView).setData((argument.capture())
    }


    @Test fun get_Data_from_presenter_dismiss_loading(){
        presenter.takeView(pagerView)
        presenter.getArticles()
        verify(pagerView).showLoading()
       // verify(pagerView).setData(articles)
       // verify(pagerView).onDataLoaded()
    }



}