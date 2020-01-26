package com.androidwind.quickaop;

import android.app.Application;

import com.androidwind.quickaop.library.QuickAOP;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class MyApplication extends Application {

    private static MyApplication INSTANCE;
    private static boolean isLogin = false;

    @Override
    public void onCreate() {
        super.onCreate();
        //get application
        if (INSTANCE == null) {
            INSTANCE = this;
        }
        QuickAOP.init(this);
    }

    public static synchronized MyApplication getApplication() {
        return INSTANCE;
    }

    public static boolean isLogin() {
        return isLogin;
    }
}