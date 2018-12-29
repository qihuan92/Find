package com.qihuan.aop

import android.util.Log
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect

/**
 * DailyAspect
 * @author qi
 * @date 2018/12/21
 */
@Aspect
class DailyAspect {

    @Around("execution(* com.qihuan.dailymodule.view.DailyFragment.onViewCreated(..))")
    fun onViewCreatedAround(joinPoint: ProceedingJoinPoint) {
        Log.e("DailyAspect", "DailyFragment::onViewCreated ---> in before")
        joinPoint.proceed()
        Log.e("DailyAspect", "DailyFragment::onViewCreated ---> in after")
    }
}