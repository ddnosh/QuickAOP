package com.androidwind.quickaop;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.androidwind.quickaop.annotation.AddView;
import com.androidwind.quickaop.annotation.Asynchronize;
import com.androidwind.quickaop.library.annotation.Cache;
import com.androidwind.quickaop.library.annotation.EventTracking;
import com.androidwind.quickaop.annotation.RequirePermission;
import com.androidwind.quickaop.library.annotation.CatchException;
import com.androidwind.quickaop.library.annotation.HookMethod;
import com.androidwind.quickaop.library.annotation.InsertLog;
import com.androidwind.quickaop.annotation.CheckLogin;
import com.androidwind.quickaop.library.annotation.NullCheck;
import com.androidwind.quickaop.library.annotation.SingleClick;
import com.blankj.utilcode.util.SPUtils;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@AddView
public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "[addView]" + "MainActivity->onCreate");
        System.out.println("[Thread Name-MainActivity: ]" + Thread.currentThread().getName());
    }

    @InsertLog
    public void insertLog(View view) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "this is an insert log.");
    }

    @SingleClick(value = 2000L, ids = {R.id.singleClick1})
    public void singleClick1(View view) {
        Log.i(TAG, "this is a single click log.");
    }

    @SingleClick(ids = {R.id.singleClick2})
    public void singleClick2(View view) {
        Log.i(TAG, "this is a single click log.");
    }

    @CheckLogin
    public void checkLogin(View view) {
        Log.i(TAG, "this is an operation after login.");
    }

    @RequirePermission(value = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public void requirePermission(View view) {
        Toast.makeText(MyApplication.getApplication(), "授权成功，继续进行", Toast.LENGTH_SHORT).show();
    }

    @EventTracking(key = "1000", value = "埋点值1")
    public void eventTracking(View view) {
        Log.i(TAG, "this is an event tracking log, the eventTracking is " + SPUtils.getInstance().getString("1000"));
    }

    @Asynchronize
    public void asynchronize(View view) {
        System.out.println("[Thread Name-asynchronize: ]" + Thread.currentThread().getName());
    }

    @CatchException
    public void catchException(View view) {
        String s = null;
        s.toString();
    }

    @HookMethod(beforeMethod = "beforeMethod", afterMethod = "afterMethod")
    public void hookMethod(View view) {
        Log.i(TAG, "this is a hookMethod");
    }

    private void beforeMethod() {
        Log.i(TAG, "this is a before method");
    }

    private void afterMethod() {
        Log.i(TAG, "this is an after method");
    }

    @Cache(key = "name")
    public String cache(View view) {
        return "Jerry";
    }

    public void nullCheck(View view) {
        getString("Tommy", null);
    }

    @NullCheck(position = 1)
    private void getString(String name, String country) {
        Log.i(TAG, "this is after nullcheck input");
    }

    public void toast(View view) {
        Toast.makeText(this,"原始的toast",Toast.LENGTH_SHORT).show();
    }
}
