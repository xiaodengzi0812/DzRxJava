package com.dengzi.rxjava.custom;


/**
 * @author Djk
 * @Title: 被观察者
 * @Time: 2017/12/5.
 * @Version:1.0.0
 */
public abstract class DzObservable<T> {

    public static <T> DzObservable just(T item) {
        return new DzObservableJust(item);
    }

    protected abstract void subscribeActual(DzObserver<? super T> var1);

    /**
     * 转换事件
     *
     * @param dzFunction
     * @return
     */
    public <R> DzObservable map(DzFunction<T, R> dzFunction) {
        return new DzObservableMap(this, dzFunction);
    }

    /**
     * 线程调度
     *
     * @param schedulers
     * @return
     */
    public DzObservable subscribeOn(DzSchedulers schedulers) {
        return new DzObservableSubscribe(this, schedulers);
    }

    public DzObservable<String> observeOn(DzSchedulers schedulers) {
        return new DzObservableObserve(this, schedulers);
    }

    /**
     * 注册一个事件
     *
     * @param dzObserver
     */
    public void subscribe(DzObserver<T> dzObserver) {
        this.subscribeActual(dzObserver);
    }


}
