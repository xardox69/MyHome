package com.myhome.app.domain.usecases

import com.myhome.app.data.AppRepository
import com.myhome.app.domain.Params
import com.myhome.app.itemslist.ArticlePagerPresenter.Companion.ARTICLE_SKU_KEY
import com.myhome.app.itemslist.ArticlePagerPresenter.Companion.DISLIKE_STATE_KEY
import com.myhome.app.itemslist.ArticlePagerPresenter.Companion.LIKE_STATE_VAL
import com.myhome.app.itemslist.ArticlePagerPresenter.Companion.STATE_KEY
import io.reactivex.Observable

class UpdateArticle constructor(private val repository: AppRepository) : BaseUseCase<Boolean>() {


    override fun getObservable(params: Params) :Observable<Boolean>{
        if(params.getInt(STATE_KEY,-1) == LIKE_STATE_VAL){
            repository.likeArticle(params.getString(ARTICLE_SKU_KEY,"")!!)
        }else if(params.getInt(STATE_KEY,-1) == DISLIKE_STATE_KEY){
            repository.disLikeArticle(params.getString(ARTICLE_SKU_KEY,"")!!)
        }

        return Observable.just(true)
    }

    }