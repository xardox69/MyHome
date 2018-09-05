package com.myhome.app

import io.reactivex.Observable

/**
 * 
 */
abstract class Mapper<in E, T> {
    abstract fun mapFrom(from: E): T

    fun mapFrom(from: List<E>): MutableList<T>{
        val toList: MutableList<T> = mutableListOf()
        from.mapTo(toList) { mapFrom(it) }
        return toList
    }

    fun observable(from: E): Observable<T> {
        return Observable.fromCallable { mapFrom(from) }
    }

    fun observable(from: List<E>): Observable<List<T>> {
        return Observable.fromCallable { from.map { mapFrom(it) } }
    }
}