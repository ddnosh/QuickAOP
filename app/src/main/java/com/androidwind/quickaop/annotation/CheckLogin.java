package com.androidwind.quickaop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 检测是否登录
 *
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface CheckLogin {
}
