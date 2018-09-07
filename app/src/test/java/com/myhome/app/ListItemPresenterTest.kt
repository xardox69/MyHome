package com.myhome.app


import com.myhome.app.data.IAppRepository
import com.myhome.app.data.model.Article
import com.myhome.app.data.model.ArticleMedia
import com.myhome.app.domain.Params
import com.myhome.app.domain.entities.ArticleModel
import com.myhome.app.domain.mapper.ArticleMapper
import com.myhome.app.domain.mapper.ArticleMediaMapper
import com.myhome.app.domain.usecases.GetArticles
import com.myhome.app.domain.usecases.UpdateArticle
import com.myhome.app.itemslist.ArticlePagerContract
import com.myhome.app.itemslist.ArticlePagerPresenter
import org.junit.Before
import org.junit.Test
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.mockito.*
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`


class ListItemPresenterTest {

    @Mock
    lateinit var appRepository: IAppRepository

    @Mock private lateinit var pagerView: ArticlePagerContract.View

    private lateinit var presenter :ArticlePagerPresenter

    private lateinit var articles: MutableList<Article>
    @Captor private lateinit var listArgumentCaptor: ArgumentCaptor<MutableList<ArticleModel>>

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


    @Test fun get_data_from_presenter_show_loading(){
        presenter.takeView(pagerView)
        presenter.getArticles()
        verify(pagerView).showLoading()
    }

    @Test fun get_data_from_presenter_show_data(){
        presenter.takeView(pagerView)
        presenter.getArticles()
        verify(pagerView).showLoading()
        verify(pagerView).onDataLoaded()
        verify(pagerView).setData(capture(listArgumentCaptor))
        assertEquals(1,listArgumentCaptor.value.size)
    }


    @Test fun update_ratings_from_presenter(){
        presenter.takeView(pagerView)
        presenter.getArticles()
        verify(pagerView).showLoading()
        verify(pagerView).showLoading()
        verify(pagerView).onDataLoaded()
        verify(pagerView).setData(capture(listArgumentCaptor))
        assertEquals(1,listArgumentCaptor.value.size)
        verify(pagerView).updateRatings(1,1)
    }

    @Test fun update_review_button_from_presenter(){
        presenter.takeView(pagerView)
        presenter.getArticles()
        verify(pagerView).showLoading()
        verify(pagerView).showLoading()
        verify(pagerView).onDataLoaded()
        verify(pagerView).setData(capture(listArgumentCaptor))
        assertEquals(1,listArgumentCaptor.value.size)
        verify(pagerView).updateRatings(1,1)
        verify(pagerView).enableReviewButton()
    }


    fun <T> capture(argumentCaptor: ArgumentCaptor<T>): T = argumentCaptor.capture()


    inline fun <reified T : Any> argumentCaptor(): ArgumentCaptor<T> =
            ArgumentCaptor.forClass(T::class.java)


    fun <T> eq(obj: T): T = Mockito.eq<T>(obj)


    /**
     * Returns Mockito.any() as nullable type to avoid java.lang.IllegalStateException when
     * null is returned.
     */
    fun <T> any(): T = Mockito.any<T>()



}