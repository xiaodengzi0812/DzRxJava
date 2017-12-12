package com.dengzi.rxjava.custom;

import io.reactivex.annotations.NonNull;

/**
 * @author Djk
 * @Title: 转换器
 * @Time: 2017/12/5.
 * @Version:1.0.0
 */
public interface DzFunction<T, R> {

    R apply(@NonNull T var1) throws Exception;
}
