package com.androidwind.quickaop.aspect;

import android.widget.Toast;

import com.androidwind.quickaop.MyApplication;
import com.androidwind.quickaop.library.QuickAOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */

@Aspect
public class CheckLoginAspect {

    private final String TAG = "CheckLoginAspect";
    private final String POINTCUT = "execution(@com.androidwind.quickaop.annotation.CheckLogin * *(..))";

    @Pointcut(POINTCUT)
    public void onCheckLoginMethod() {
    }

    @Around("onCheckLoginMethod()")
    public void doCheckLoginMethod(ProceedingJoinPoint joinPoint) throws Throwable {
       if (MyApplication.isLogin()) {
           joinPoint.proceed();//执行原方法
       } else {
           Toast.makeText(MyApplication.getApplication(), "还未登录", Toast.LENGTH_SHORT).show();
       }
    }
}
