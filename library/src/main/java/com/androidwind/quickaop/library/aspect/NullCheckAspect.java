package com.androidwind.quickaop.library.aspect;

import com.androidwind.quickaop.library.annotation.NullCheck;
import com.blankj.utilcode.util.ObjectUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */

@Aspect
public class NullCheckAspect {

    private final String TAG = "NullCheckAspect";
    private final String POINTCUT = "execution(@com.androidwind.quickaop.library.annotation.NullCheck * *(..))";

    @Pointcut(POINTCUT)
    public void onNullCheckMethod() {
    }

    @Around("onNullCheckMethod() && @annotation(nullCheck)")
    public void doNullCheckMethod(ProceedingJoinPoint joinPoint, NullCheck nullCheck) throws Throwable {
        int position = nullCheck.position();
        Object[] objects = joinPoint.getArgs();
        if (objects.length > 0 && position < objects.length) {
            if (!ObjectUtils.isEmpty(objects[position])) {
                joinPoint.proceed();
            }
        }
    }
}
