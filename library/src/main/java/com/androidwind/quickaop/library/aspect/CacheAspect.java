package com.androidwind.quickaop.library.aspect;

import com.androidwind.quickaop.library.annotation.Cache;
import com.blankj.utilcode.util.SPUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.io.Serializable;

/**
 * description $desc$
 * created by jerry on 2019/5/30.
 */
@Aspect
public class CacheAspect {
    private static final String POINTCUT_METHOD = "execution(@com.androidwind.quickaop.library.annotation.Cache * *(..))";

    @Pointcut(POINTCUT_METHOD)
    public void onCacheMethod() {
    }

    @Around("onCacheMethod() && @annotation(cache)")
    public Object doCacheMethod(ProceedingJoinPoint joinPoint, Cache cache) throws Throwable {
        String key = cache.key();

        Object result = joinPoint.proceed();
        if (result instanceof String) {
            SPUtils.getInstance().put(key, (String)result);
        }

        return result;
    }
}
