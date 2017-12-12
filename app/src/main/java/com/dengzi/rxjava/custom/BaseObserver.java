package com.dengzi.rxjava.custom;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * @author Djk
 * @Title:
 * @Time: 2017/12/5.
 * @Version:1.0.0
 */

public class BaseObserver<T> implements DzObserver<T> {
    @Override
    public void onSubscribe(@NonNull Disposable var1) {

    }

    @Override
    public void onNext(@NonNull T var1) {

    }

    @Override
    public void onError(@NonNull Throwable var1) {

    }

    @Override
    public void onComplete() {

    }
}
