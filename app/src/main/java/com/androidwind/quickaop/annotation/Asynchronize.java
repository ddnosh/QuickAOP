package com.androidwind.quickaop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 进行异步处理
 *
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Asynchronize {
}
