package com.androidwind.quickaop.aspect;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.concurrent.TimeUnit;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@Aspect
public class InsertLogAspect {

    private final String TAG = "InsertLogAspect";
    private final String POINTCUT = "execution(@com.androidwind.quickaop.annotation.InsertLog * *(..))";
    private long startTime, endTime;
    private String className;
    private String methodName;

    @Pointcut(POINTCUT)//切点annotation
    public void onInsertLogMethod() {

    }

    @Before("onInsertLogMethod()")//Before:在原方法前面插入
    public void before(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        className = methodSignature.getDeclaringType().getSimpleName();
        methodName = methodSignature.getName();
        startTime = System.nanoTime();
        Log.i(TAG, className + "." + methodName + "'s start time: " + startTime);
    }

    /**
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("onInsertLogMethod()")//重写原方法
    public void doInsertLogMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        Log.i(TAG, "Around before proceed:" + className + "." + methodName);
        //
        Object result = joinPoint.proceed();//执行原方法
        //
        Log.i(TAG, "Around after proceed:" + className + "." + methodName);
    }

    @After("onInsertLogMethod()")//After:在原方法后面插入, 注意要写在Around后
    public void after() {
        endTime = System.nanoTime();
        Log.i(TAG, className + "." + methodName + "'s end time: " + endTime);
        Log.i(TAG, className + "." + methodName + " spent: " + (TimeUnit.NANOSECONDS.toMillis(endTime - startTime)));//换算成毫秒
    }
}
