package com.androidwind.quickaop.library.aspect;

import android.util.Log;
import android.view.View;

import com.androidwind.library.R;
import com.androidwind.quickaop.library.annotation.SingleClick;

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

    private static int TIME_TAG = R.id.click_time;

    @Pointcut("execution(@com.androidwind.quickaop.library.annotation.SingleClick * *(..))")
    public void onSingleClickMethod() {
    }

    @Around("onSingleClickMethod() && @annotation(singleClick)")
    public void doSingleClickMethod(ProceedingJoinPoint joinPoint, SingleClick singleClick) throws Throwable {
        Log.d("SingleClickAspect", "singleClick=" + singleClick.hashCode());
        View view = null;
        for (Object arg : joinPoint.getArgs()) {
            if (arg instanceof View) {
                view = (View) arg;
                break;
            }
        }
        if (view != null) {
            Object tag = view.getTag(TIME_TAG);
            long lastClickTime = ((tag != null) ? (long) tag : 0);
            Log.d("SingleClickAspect", "lastClickTime:" + lastClickTime);
            long currentTime = Calendar.getInstance().getTimeInMillis();
            long value = singleClick.value();
            int[] ids = singleClick.ids();
            if (currentTime - lastClickTime > value || !hasId(ids, view.getId())) {
                view.setTag(TIME_TAG, currentTime);
                Log.d("SingleClickAspect", "currentTime:" + currentTime);
                joinPoint.proceed();//执行原方法
            }

        }
    }

    public static boolean hasId(int[] arr, int value) {
        for (int i : arr) {
            if (i == value)
                return true;
        }
        return false;
    }
}
