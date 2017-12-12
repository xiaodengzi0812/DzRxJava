package com.dengzi.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableOperator;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class DemoActivity5 extends AppCompatActivity {
    public static final String TAG = "tag";
    private TextView tv;
    private List<String> strList = new ArrayList<>();

    private String[] strs = new String[]{"a", "b", "c", "d"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        tv = (TextView) findViewById(R.id.tv);
        init();
    }

    private void init() {
        for (int i = 0; i < 5; i++) {
            strList.add("str " + i);
        }
    }

    public void click(View view) {

        Observable
                .fromArray(strs)
                .lift(new ObservableOperator<String, String>() {
                    @Override
                    public Observer<? super String> apply(@NonNull Observer<? super String> observer) throws Exception {
                        return observer;
                    }
                })
                .flatMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(@NonNull String s) throws Exception {
                        return Observable.just(s);
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.e("dengzi", "s -> " + s);
                    }
                });


/*
        Observable
                .fromArray(strList)
                .flatMap(new Function<List<String>, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(@NonNull final List<String> strings) throws Exception {
                        return new ObservableSource() {
                            @Override
                            public void subscribe(@NonNull Observer observer) {
                                for (String string : strings) {
                                    observer.onNext(string);
                                }
                            }
                        };
                    }
                })
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(@NonNull String s) throws Exception {
                        return s.contains("2");
                    }
                })
                .map(new Function<String, String>() {
                    @Override
                    public String apply(@NonNull String s) throws Exception {
                        return s + "map";
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.e("dengzi", "s -> " + s);
                    }
                });*/
    }

}
