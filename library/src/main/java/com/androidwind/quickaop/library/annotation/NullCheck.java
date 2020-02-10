package com.androidwind.quickaop.library.annotation;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 非空检查
 *
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface NullCheck {
    int position() default 0;//the input params position
}
