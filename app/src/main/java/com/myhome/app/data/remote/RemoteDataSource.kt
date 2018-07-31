package com.myhome.app.data.remote

import com.myhome.app.data.model.GetItemsResponse
import com.myhome.app.data.remote.APIConstants.Companion.CURENT_LIMIT
import com.myhome.app.data.remote.APIConstants.Companion.CURRENT_DOMAIN
import com.myhome.app.data.remote.APIConstants.Companion.CURRENT_LOCALE
import io.reactivex.Observable
import io.reactivex.Scheduler
import okhttp3.OkHttpClient

import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory


class RemoteDataSource constructor(private val subscriberScheduler: Scheduler,
                                   private val observerScheduler: Scheduler) : IRemoteDataSource {
    override fun getArticles(): Observable<Response<GetItemsResponse>> {
       return service.getArticles(CURRENT_DOMAIN, CURRENT_LOCALE, CURENT_LIMIT)
    }


    companion object {

        @Volatile
        private var INSTANCE: RemoteDataSource? = null

        fun getInstance(subscriberScheduler: Scheduler,
                        observerScheduler: Scheduler): RemoteDataSource {
            if (INSTANCE == null) {
                INSTANCE = RemoteDataSource(subscriberScheduler, observerScheduler)

            }
            return INSTANCE as RemoteDataSource
        }
    }


    private var  baseUrl: String = "https://api.github.com"

    private var httpClient: OkHttpClient = getHTTPClinet()


    private var retrofit: Retrofit = getRetrofit()

    private var service: NetworkService = getService()

    private fun getHTTPClinet(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(getLoggingInterceptor()) //for logging requests
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS).build()
    }


    private  fun getService() : NetworkService{
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
            logger.setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            logger.setLevel(HttpLoggingInterceptor.Level.NONE)
        }
        return logger
    }









}