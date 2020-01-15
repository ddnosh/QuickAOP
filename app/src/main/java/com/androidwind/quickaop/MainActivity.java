package com.androidwind.quickaop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.androidwind.quickaop.annotation.SingleClick;
import com.androidwind.quickaop.annotation.InsertLog;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    @SingleClick(value = 2000L)
    public void singleClick(View view) {
        Log.i(TAG, "this is a single click log.");
    }
}
