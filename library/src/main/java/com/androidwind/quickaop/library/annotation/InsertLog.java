package com.androidwind.quickaop.library.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 带有统计耗时功能的log
 *
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.METHOD})
public @interface InsertLog {
}
