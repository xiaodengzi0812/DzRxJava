package com.dengzi.rxjava.custom;

import io.reactivex.annotations.NonNull;

/**
 * @author Djk
 * @Title:
 * @Time: 2017/12/5.
 * @Version:1.0.0
 */
public class DzObservableSubscribe extends DzObservable {
    DzObservable observable;
    DzSchedulers schedulers;

    public DzObservableSubscribe(DzObservable observable, DzSchedulers schedulers) {
        this.observable = observable;
        this.schedulers = schedulers;
    }

    @Override
    protected void subscribeActual(final DzObserver observer) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                observable.subscribe(new SubscribeObserver(observer));
            }
        };
        schedulers.execute(runnable);
    }

    static class SubscribeObserver extends BaseObserver {
        DzObserver observer;

        public SubscribeObserver(DzObserver observer) {
            this.observer = observer;
        }

        @Override
        public void onNext(@NonNull Object var1) {
            observer.onNext(var1);
        }
    }

}