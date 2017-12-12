package com.dengzi.rxjava.rxpermission;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.dengzi.rxjava.R;
import com.dengzi.rxjava.rxpermission.util.DzPermissions;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;

public class PermissionDemoActivity3 extends AppCompatActivity {
    DzPermissions dzPermissions;
    final String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        dzPermissions = new DzPermissions(this);
    }

    public void click(View view) {
        // 申请操作
        dzPermissions
                .request(permissions)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        // 不管同时申请多少权限，都只有一个boolean的回调
                        // 一次申请的所有权限全部同意才为true，否则为false
                        Log.e("dengzi", "申请结果 = " + aBoolean);
                    }
                });
    }
}
