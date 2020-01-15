package com.androidwind.quickaop;

import android.app.Application;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class MyApplication extends Application {

    private static MyApplication INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        //get application
        if (INSTANCE == null) {
            INSTANCE = this;
        }
    }

    public static synchronized MyApplication getApplication() {
        return INSTANCE;
    }
}