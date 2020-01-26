package com.androidwind.quickaop.library.aspect;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */

@Aspect
public class CatchExceptionAspect {

    private final String TAG = "CatchExceptionAspect";
    private final String POINTCUT = "execution(@com.androidwind.quickaop.library.annotation.CatchException * *(..))";

    @Pointcut(POINTCUT)
    public void onCatchExceptionMethod() {
    }

    @Around("onCatchExceptionMethod()")
    public void doCatchExceptionMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            joinPoint.proceed();
        } catch (Exception e) {
            Log.i(TAG, e.getMessage() + "");
        }
    }
}
