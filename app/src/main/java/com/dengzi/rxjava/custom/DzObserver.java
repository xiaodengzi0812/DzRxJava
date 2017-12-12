package com.dengzi.rxjava.custom;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * @author Djk
 * @Title: 观察者
 * @Time: 2017/12/5.
 * @Version:1.0.0
 */
public interface DzObserver<T> {
    void onSubscribe(@NonNull Disposable var1);

    void onNext(@NonNull T var1);

    void onError(@NonNull Throwable var1);

    void onComplete();
}
