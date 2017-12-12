package com.dengzi.rxjava.rxpermission.util;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;

import io.reactivex.Observable;

/**
 * @author Djk
 * @Title:
 * @Time: 2017/12/6.
 * @Version:1.0.0
 */
public class DzPermissions {
    // 创建Fragment所带的Tag，为了复用Fragment
    private String TAG = "TAG";
    // 创建的Fragment实例
    private DzPermissionFragment mPermissionFragment;

    public DzPermissions(Activity activity) {
        // 创建一个隐藏的Fragment
        mPermissionFragment = getPermissionFragment(activity);
    }

    /**
     * 获取PermissionFragment实例
     *
     * @param activity
     * @return
     */
    public DzPermissionFragment getPermissionFragment(Activity activity) {
        FragmentManager fragmentManager = activity.getFragmentManager();
        // 先根据Tag查找有没有已有的Fragment
        Fragment fragment = fragmentManager.findFragmentByTag(TAG);
        if (fragment == null) {
            // 直接创建Fragment，并添加到Activity中
            fragment = new DzPermissionFragment();
            fragmentManager
                    .beginTransaction()
                    .add(fragment, TAG)
                    .commitAllowingStateLoss();
            // 设置Fragment为透明
            fragmentManager.executePendingTransactions();
        }
        return (DzPermissionFragment) fragment;
    }

    /**
     * 请求权限
     *
     * @param permissions
     * @return
     */
    public Observable request(String[] permissions) {
        // 直接调用Fragment中的申请权限方法
        return mPermissionFragment.request(permissions);
    }
}
