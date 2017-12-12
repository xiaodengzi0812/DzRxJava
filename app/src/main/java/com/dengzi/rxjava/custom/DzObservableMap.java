package com.dengzi.rxjava.custom;

import io.reactivex.annotations.NonNull;

/**
 * @author Djk
 * @Title:
 * @Time: 2017/12/5.
 * @Version:1.0.0
 */
public class DzObservableMap<T, R> extends DzObservable<T> {
    DzObservable observable;
    DzFunction function;

    public DzObservableMap(DzObservable observable, DzFunction<T, R> dzFunction) {
        this.observable = observable;
        this.function = dzFunction;
    }

    @Override
    protected void subscribeActual(DzObserver<? super T> observer) {
        observable.subscribe(new MapObserver(observer, this.function));
    }

    static class MapObserver<T> extends BaseObserver<T> {
        private DzObserver observer;
        private DzFunction function;

        public MapObserver(DzObserver<? super T> observer, DzFunction function) {
            this.observer = observer;
            this.function = function;
        }

        @Override
        public void onNext(@NonNull T var1) {
            try {
                Object obj = function.apply(var1);
                observer.onNext(obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

