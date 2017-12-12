package com.dengzi.rxjava;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.dengzi.rxjava.custom.DemoActivity4;
import com.dengzi.rxjava.rxpermission.PermissionDemoActivity;
import com.dengzi.rxjava.rxpermission.PermissionDemoActivity2;
import com.dengzi.rxjava.rxpermission.PermissionDemoActivity3;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click0(View view) {
        startActivity(new Intent(MainActivity.this, DemoActivity0.class));
    }

    public void click1(View view) {
        startActivity(new Intent(MainActivity.this, DemoActivity1.class));
    }

    public void click2(View view) {
        startActivity(new Intent(MainActivity.this, DemoActivity2.class));
    }

    public void click3(View view) {
        startActivity(new Intent(MainActivity.this, DemoActivity3.class));
    }

    public void click4(View view) {
        startActivity(new Intent(MainActivity.this, DemoActivity4.class));
    }

    public void click5(View view) {
        startActivity(new Intent(MainActivity.this, DemoActivity5.class));
    }

    public void permClick(View view) {
        startActivity(new Intent(MainActivity.this, PermissionDemoActivity.class));
    }

    public void permClick2(View view) {
        startActivity(new Intent(MainActivity.this, PermissionDemoActivity2.class));
    }

    public void permClick3(View view) {
        startActivity(new Intent(MainActivity.this, PermissionDemoActivity3.class));
    }

}
