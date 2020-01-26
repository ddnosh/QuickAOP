package com.androidwind.quickaop.aspect;

import android.widget.Toast;

import com.androidwind.quickaop.MyApplication;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */

@Aspect
public class AsynchronizeAspect {

    private final String TAG = "AsynchronizeAspect";
    private final String POINTCUT = "execution(@com.androidwind.quickaop.annotation.Asynchronize * *(..))";

    @Pointcut(POINTCUT)
    public void onAsynchronizeMethod() {
    }

    @Around("onAsynchronizeMethod()")
    public void doAsynchronizeMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                System.out.println("[Thread Name-AsynchronizeAspect: ]" + Thread.currentThread().getName());
                try {
                    joinPoint.proceed();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
}
