package com.androidwind.quickaop.library;

import android.app.Application;
import android.content.Context;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class QuickAOP {

    private static Application sApplication;
    private static Context sContext;

    public static Application getApplication() {
        return sApplication;
    }

    public static Context getContext() {
        return sApplication.getApplicationContext();
    }

    public static void init(Application application) {
        sApplication = application;
    }
}
