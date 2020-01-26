package com.androidwind.quickaop.library.aspect;

import android.util.Log;

import com.androidwind.quickaop.library.annotation.HookMethod;
import com.blankj.utilcode.util.ReflectUtils;
import com.blankj.utilcode.util.StringUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

import androidx.core.util.Preconditions;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */

@Aspect
public class HookMethodAspect {

    private final String TAG = "HookMethodAspect";
    private final String POINTCUT = "execution(@com.androidwind.quickaop.library.annotation.HookMethod * *(..))";

    @Pointcut(POINTCUT)
    public void onHookMethodMethod() {
    }

    @Around("onHookMethodMethod()")
    public void doHookMethodMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        HookMethod hookMethod = method.getAnnotation(HookMethod.class);

        if (hookMethod == null) {
            return;
        }

        String beforeMethod = hookMethod.beforeMethod();
        String afterMethod = hookMethod.afterMethod();

        if (!StringUtils.isEmpty(beforeMethod)) {
            try {
                ReflectUtils.reflect(joinPoint.getTarget()).method(beforeMethod);
            } catch (ReflectUtils.ReflectException e) {
                e.printStackTrace();
                Log.e(TAG, "no method " + beforeMethod);
            }
        }

        joinPoint.proceed();

        if (!StringUtils.isEmpty(afterMethod)) {
            try {
                ReflectUtils.reflect(joinPoint.getTarget()).method(afterMethod);
            } catch (ReflectUtils.ReflectException e) {
                e.printStackTrace();
                Log.e(TAG, "no method " + afterMethod);
            }
        }
    }
}
