package com.myhome.app.data.remote

import com.myhome.app.data.model.Article
import com.myhome.app.data.remote.APIConstants.CURRENT_DOMAIN
import com.myhome.app.data.remote.APIConstants.CURRENT_LIMIT
import com.myhome.app.data.remote.APIConstants.CURRENT_LOCALE
import com.myhome.app.domain.Params
import io.reactivex.Observable
import io.reactivex.Scheduler
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Remote data source to get the data from remote location
 */
class RemoteDataSource @Inject constructor(private val subscriberScheduler: Scheduler,
                                           private val observerScheduler: Scheduler) : IRemoteDataSource {


    override fun getArticles(params: Params): Observable<MutableList<Article>> {
        return service.getArticles(params.getString(APIConstants.APP_DOMAIN, CURRENT_DOMAIN)!!, params.getString(APIConstants.LOCALE, CURRENT_LOCALE)!!,
                params.getInt(APIConstants.LIMIT, CURRENT_LIMIT)).subscribeOn(subscriberScheduler)
                .observeOn(observerScheduler).flatMap { response1 ->
                    if (response1.isSuccessful) {
                        (Observable.just(response1.body()!!.objects.articles))
                    } else {
                        (Observable.just(arrayListOf()))
                    }
                }
    }


    private var baseUrl: String = "https://api-mobile.home24.com/api/v2.0/"

    private var httpClient: OkHttpClient = getHTTPClinet()


    private var retrofit: Retrofit = getRetrofit()

    private var service: NetworkService = getService()

    private fun getHTTPClinet(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(getLoggingInterceptor()) //for logging requests
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS).build()
    }


    private fun getService(): NetworkService {
        return retrofit.create(NetworkService::class.java)
    }


    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient)
                .build()
    }

    private fun getLoggingInterceptor(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor()
        //TODO: For now just log
        val isDebug = true
        if (isDebug) {
            logger.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logger.level = HttpLoggingInterceptor.Level.NONE
        }
        return logger
    }


}