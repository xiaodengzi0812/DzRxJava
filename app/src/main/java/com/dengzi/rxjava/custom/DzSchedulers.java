package com.dengzi.rxjava.custom;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * @author Djk
 * @Title:
 * @Time: 2017/12/12.
 * @Version:1.0.0
 */
public class DzSchedulers {

    public static DzSchedulers io() {
        return new IoSchedulers();
    }

    public static DzSchedulers mainThread() {
        return new MainSchedulers();
    }

    public void execute(Runnable runnable) {
    }

    private static class IoSchedulers extends DzSchedulers {
        @Override
        public void execute(final Runnable runnable) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    runnable.run();
                }
            }).start();
        }
    }

    private static class MainSchedulers extends DzSchedulers {
        Handler handler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(final Runnable runnable) {
            Message msg = Message.obtain(handler, new Runnable() {
                @Override
                public void run() {
                    runnable.run();
                }
            });
            handler.sendMessage(msg);
        }
    }
}
