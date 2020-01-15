package com.androidwind.quickaop.aspect;

import android.util.Log;
import android.view.View;

import com.androidwind.quickaop.R;
import com.androidwind.quickaop.annotation.SingleClick;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Calendar;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */

@Aspect
public class SingleClickAspect {

    private final String TAG = "SingleClickAspect";

    @Pointcut("execution(@com.androidwind.quickaop.annotation.SingleClick * *(..))")
    public void onSingleClickMethod() {
    }

    @Around("onSingleClickMethod() && @annotation(singleClick)")
    public void doSingleClickMethod(ProceedingJoinPoint joinPoint, SingleClick singleClick) throws Throwable {
        long value = singleClick.value();
        Log.i(TAG, "Around proceed:" + value);
        joinPoint.proceed();//执行原方法
    }
}
