package com.androidwind.quickaop.aspect;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.androidwind.quickaop.MyApplication;
import com.androidwind.quickaop.annotation.RequirePermission;
import com.androidwind.quickaop.library.QuickAOP;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import io.reactivex.functions.Consumer;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */

@Aspect
public class RequirePermissionAspect {

    private final String TAG = "RequirePermissionAspect";
    private final String POINTCUT = "execution(@com.androidwind.quickaop.annotation.RequirePermission * *(..))";

    @Pointcut(POINTCUT)
    public void onRequirePermissionMethod() {
    }

    @Around("onRequirePermissionMethod() && @annotation(requirePermission)")
    public void doRequirePermissionMethod(ProceedingJoinPoint joinPoint, RequirePermission requirePermission) throws Throwable {
        FragmentActivity activity = null;
        final Object object = joinPoint.getThis();
        if (object instanceof FragmentActivity) {
            activity = (FragmentActivity) object;
        } else if (object instanceof Fragment) {
            activity = ((Fragment) object).getActivity();
        }
        if (activity == null) {
            joinPoint.proceed();
        } else {
            new RxPermissions(activity)
                    .request(requirePermission.value())
                    .subscribe(new Consumer<Boolean>() {
                        @Override
                        public void accept(Boolean granted) throws Exception {
                            if (granted) { // Always true pre-M
                                try {
                                    joinPoint.proceed();
                                } catch (Throwable throwable) {
                                    throwable.printStackTrace();
                                }
                            } else {
                                Toast.makeText(MyApplication.getApplication(), "授权失败！", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}
