package com.myhome.app

import com.myhome.app.data.AppRepository
import com.myhome.app.data.local.LocalDataSource
import com.myhome.app.data.local.room.DataDao
import com.myhome.app.data.model.Article
import com.myhome.app.data.model.ArticleMedia
import com.myhome.app.data.remote.RemoteDataSource
import com.myhome.app.domain.mapper.ArticleMapper
import com.myhome.app.domain.mapper.ArticleMediaMapper
import com.myhome.app.domain.usecases.GetArticles
import com.myhome.app.domain.usecases.UpdateArticle
import com.myhome.app.itemslist.ArticlePagerContract
import com.myhome.app.itemslist.ArticlePagerPresenter
import com.myhome.app.reviewscreen.ReviewContract
import com.myhome.app.reviewscreen.ReviewPresenter
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ReviewScreenPresenterTest{

@Mock
lateinit var dao: DataDao

lateinit var remoteDataSource: RemoteDataSource
lateinit var localDataSource: LocalDataSource

@Mock
private lateinit var view: ReviewContract.View

private lateinit var presenter : ReviewPresenter

private lateinit var articles: MutableList<Article>

@Before
fun  setupPresenter() {
    MockitoAnnotations.initMocks(this);

    remoteDataSource = RemoteDataSource(Schedulers.trampoline(), Schedulers.trampoline())
    localDataSource = LocalDataSource(dao,Schedulers.trampoline(), Schedulers.trampoline())

    var appRepository: AppRepository = AppRepository(remoteDataSource, localDataSource)

    var getArticles: GetArticles = GetArticles(appRepository, ArticleMapper(ArticleMediaMapper()))
    var updateArticle: UpdateArticle = UpdateArticle(appRepository)

    presenter = ReviewPresenter(getArticles, Schedulers.trampoline(), Schedulers.trampoline())

    articles = arrayListOf<Article>()

    var article: Article = Article("000", "asasd", true, false, arrayListOf<ArticleMedia>())

    articles.add(article)


    Mockito.`when`(dao.getArticles()).thenReturn(articles)


}

    @Test
    fun get_Data_from_presenter_show_loading(){
        presenter.takeView(view)
        presenter.getArticles()
        Mockito.verify(view).showLoading()
    }

    @Test
    fun get_Data_from_presenter_show_data(){
        presenter.takeView(view)
        presenter.getArticles()
        Mockito.verify(view).showLoading()
      //  Mockito.verify(view).setData(articles)
    }


    @Test
    fun get_Data_from_presenter_dismiss_loading(){
        presenter.takeView(view)
        presenter.getArticles()
        Mockito.verify(view).showLoading()
      //  Mockito.verify(view).setData(articles)
       // Mockito.verify(view).dismissLoading()
    }

}