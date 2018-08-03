package com.myhome.app.domain.usecases


import com.myhome.app.domain.Params
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver


abstract class BaseUseCase<T>(private val disposables: CompositeDisposable = CompositeDisposable()) {

    protected abstract fun getObservable(params: Params): Observable<T>

    /**
     * Executes the current UseCase.
     *
     * @param observer [DisposableObserver] which will be listening to the observable build
     * with [.getObservable].
     */
    fun execute(observer: DisposableObserver<T>, params: Params?, subscriberSchedulers: Scheduler,
                observerSchedulers: Scheduler) {
        val observable = this.getObservable(params!!)
                .subscribeOn(subscriberSchedulers)
                .observeOn(observerSchedulers)
        addDisposable(observable.subscribeWith<DisposableObserver<T>>(observer))
    }


    /**
     * Dispose from current [CompositeDisposable].
     */
    fun dispose() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    private fun addDisposable(disposable: Disposable?) {
        if (disposable != null) {
            disposables.add(disposable)
        }
    }
}