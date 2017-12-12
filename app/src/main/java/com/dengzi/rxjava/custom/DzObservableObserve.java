package com.dengzi.rxjava.custom;

import io.reactivex.annotations.NonNull;

/**
 * @author Djk
 * @Title:
 * @Time: 2017/12/5.
 * @Version:1.0.0
 */
public class DzObservableObserve extends DzObservable {
    DzObservable observable;
    DzSchedulers schedulers;

    public DzObservableObserve(DzObservable observable, DzSchedulers schedulers) {
        this.observable = observable;
        this.schedulers = schedulers;
    }

    @Override
    protected void subscribeActual(final DzObserver observer) {
        observable.subscribe(new ObserveObserver(observer, schedulers));
    }

    static class ObserveObserver extends BaseObserver {
        DzObserver observer;
        DzSchedulers schedulers;

        public ObserveObserver(DzObserver observer, DzSchedulers schedulers) {
            this.observer = observer;
            this.schedulers = schedulers;
        }

        @Override
        public void onNext(@NonNull final Object var1) {
            schedulers.execute(new Runnable() {
                @Override
                public void run() {
                    observer.onNext(var1);
                }
            });
        }
    }

}