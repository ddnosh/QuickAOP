package com.androidwind.quickaop.aspect;

import android.widget.Toast;

import com.androidwind.quickaop.MyApplication;
import com.androidwind.quickaop.annotation.EventTracking;
import com.androidwind.quickaop.annotation.RequirePermission;
import com.androidwind.quickaop.library.util.SPUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */

@Aspect
public class EventTrackingAspect {

    private final String TAG = "EventTrackingAspect";
    private final String POINTCUT = "execution(@com.androidwind.quickaop.annotation.EventTracking * *(..))";

    @Pointcut(POINTCUT)
    public void onEventTrackingMethod() {
    }

    @Around("onEventTrackingMethod() && @annotation(eventTracking)")
    public void doEventTrackingMethod(ProceedingJoinPoint joinPoint, EventTracking eventTracking) throws Throwable {

        String key = eventTracking.key();
        String value = eventTracking.value();
        SPUtils.getInstance().put(key, value);
        joinPoint.proceed();
    }
}
