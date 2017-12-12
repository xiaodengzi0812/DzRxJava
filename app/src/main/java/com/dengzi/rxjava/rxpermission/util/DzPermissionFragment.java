package com.dengzi.rxjava.rxpermission.util;

import android.app.Fragment;
import android.content.pm.PackageManager;
import android.os.SystemClock;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

/**
 * @author Djk
 * @Title:
 * @Time: 2017/12/6.
 * @Version:1.0.0
 */
public class DzPermissionFragment extends Fragment {
    private final int REQUEST_PHONE_PERMISSIONS = 0x0011;
    // 获取的ObservableEmitter
    private ObservableEmitter mEmitter;

    /**
     * 请求权限
     *
     * @param permissions
     * @return
     */
    public Observable request(final String[] permissions) {
        // RxJava写法，直接创建一个源
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Boolean> observableEmitter) throws Exception {
                requestPermissions(permissions, REQUEST_PHONE_PERMISSIONS);
                mEmitter = observableEmitter;
            }
        });
    }

    /**
     * 申请权限的回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PHONE_PERMISSIONS) {
            if (permissions != null) {
                // 判断所有的权限申请成功
                int grant = permissions.length;
                for (int i = 0; i < permissions.length; i++) {
                    // 判断权限的回调码，如果等于PackageManager.PERMISSION_GRANTED，则将grant--
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        grant--;
                    }
                }
                // 发送一个消息，如果grant == 0，说明上面的for循环申请的权限全部成功
                mEmitter.onNext(grant == 0);
            }
        }
    }
}
