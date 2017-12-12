package com.dengzi.rxjava.rxpermission;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.dengzi.rxjava.R;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BooleanSupplier;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class PermissionDemoActivity2 extends AppCompatActivity {
    private TextView tv;
    private final int REQUEST_PHONE_PERMISSIONS = 0;

    private Observable mRequestObservable;
    private volatile int state = -1;

    private Handler handler = new Handler() {

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        tv = (TextView) findViewById(R.id.tv);

        request();
    }

    private void request() {
        final String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        mRequestObservable = Observable
                .just("Request")
                .flatMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(@NonNull String s) throws Exception {
                        requestPermissions(permissions, REQUEST_PHONE_PERMISSIONS);
                        return Observable.just("flatMap");
                    }
                })
                .observeOn(Schedulers.newThread())
                .flatMap(new Function<String, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(@NonNull String s) throws Exception {
                        Log.e("dengzi", "1");
                        while (state == -1) {
                            Log.e("dengzi", "2");
                            SystemClock.sleep(500);
                        }
                        Log.e("dengzi", "3");
                        return Observable.just(state == 0);
                    }
                });
    }

    public void click(View view) {
        state = -1;
        mRequestObservable.subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                Log.e("dengzi", "申请结果 = " + aBoolean);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_PHONE_PERMISSIONS:
                if (permissions != null) {
                    int grant = permissions.length;
                    for (int i = 0; i < permissions.length; i++) {
                        String permission = permissions[i];
                        int temp = grantResults[i];
                        if (temp == PackageManager.PERMISSION_GRANTED) {
                            grant--;
                        }
                    }
                    state = grant;
                    Log.e("dengzi", "Result-> " + state);
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
