package com.dengzi.rxjava.custom;

/**
 * @author Djk
 * @Title:
 * @Time: 2017/12/5.
 * @Version:1.0.0
 */
public class DzObservableJust<T> extends DzObservable<T> {
    private final T item;

    public DzObservableJust(T item) {
        this.item = item;
    }

    @Override
    protected void subscribeActual(DzObserver<? super T> observer) {
        observer.onNext(this.item);
    }
}
