package com.dengzi.rxjava.custom;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.dengzi.rxjava.R;

import java.lang.reflect.Method;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class DemoActivity4 extends AppCompatActivity {
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        tv = (TextView) findViewById(R.id.tv);
    }

    public void click(View view) {
        DzObservable
                .just("是非骚贱转头空！")
                .map(new DzFunction<String, String>() {
                    @Override
                    public String apply(@NonNull String s) throws Exception {
                        Log.e("dengzi", "1->" + Thread.currentThread().getName());
                        return s + "青山依旧在，几度夕阳红！";
                    }
                })
                .subscribeOn(DzSchedulers.mainThread())
                .observeOn(DzSchedulers.mainThread())
                .map(new DzFunction<String, String>() {
                    @Override
                    public String apply(@NonNull String s) throws Exception {
                        Log.e("dengzi", "1->" + Thread.currentThread().getName());
                        return s + "古今多少事,都赋笑谈中!";
                    }
                })
                .subscribe(new DzObserver<String>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable var1) {

                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        Log.e("dengzi", "1->" + Thread.currentThread().getName());
                        Log.e("dengzi", "onNext = " + s);
                    }

                    @Override
                    public void onError(@NonNull Throwable var1) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        /*Observable
                .just("是非骚贱转头空！")
                .map(new Function<String, String>() {
                    @Override
                    public String apply(@NonNull String s) throws Exception {
                        Log.e("dengzi", "1->" + Thread.currentThread().getName());
                        return s + "青山依旧在，几度夕阳红！";
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<String, String>() {
                    @Override
                    public String apply(@NonNull String s) throws Exception {
                        Log.e("dengzi", "2->" + Thread.currentThread().getName());
                        return s + "青山依旧在，几度夕阳红！";
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable disposable) {
                        Log.e("dengzi", "onSubscribe");
                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        Log.e("dengzi", "onNext = " + s);
                        Log.e("dengzi", "3->" + Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(@NonNull Throwable throwable) {

                    }

                    @Override
                    public void onComplete() {
                        Log.e("dengzi", "onComplete");
                    }
                });*/
    }

    private void method() {
        Observable
                .just("是非骚贱转头空！")
                .map(new Function<String, String>() {
                    @Override
                    public String apply(@NonNull String s) throws Exception {
                        return s + "青山依旧在，几度夕阳红！";
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable disposable) {
                        Log.e("dengzi", "onSubscribe");
                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        Log.e("dengzi", "onNext = " + s);
                    }

                    @Override
                    public void onError(@NonNull Throwable throwable) {

                    }

                    @Override
                    public void onComplete() {
                        Log.e("dengzi", "onComplete");
                    }
                });
    }

}
