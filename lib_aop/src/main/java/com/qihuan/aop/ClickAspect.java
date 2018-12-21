package com.qihuan.aop;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * ClickAspect
 *
 * @author qi
 * @date 2018/12/21
 */
@Aspect
public class ClickAspect {
    private static Long lastClickTime = 0L;
    private static final Long FILTER_TIME = 1000L;

    @Around("execution(* android.view.View.OnClickListener.onClick(..))")
    public void aroundClick(ProceedingJoinPoint joinPoint) throws Throwable {
        if (System.currentTimeMillis() - lastClickTime >= FILTER_TIME) {
            lastClickTime = System.currentTimeMillis();
            joinPoint.proceed();
        } else {
            Log.e("ClickAspect", "重复点击,已过滤");
        }
    }
}
