package com.tufei.mvvmkotlin.aop

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.view.View
import com.tufei.mvvmkotlin.R
import com.tufei.mvvmkotlin.util.isNetworkAvailable
import com.tufei.mvvmkotlin.util.showToast
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature


/**
 * 网络状态切面（仅用于View、Activity、Fragment）
 * @author tufei
 * @date 2018/2/28.
 */
@Aspect
class NetAspect {

    /**
     * 找到处理的切点
     * * *(..)  可以处理所有的方法
     */
    @Pointcut("execution(@com.tufei.mvvmkotlin.aop.NetCheck * *(..))")
    fun checkNetBehavior() {
    }

    /**
     * 处理切面
     */
    @Around("checkNetBehavior()")
    @Throws(Throwable::class)
    fun checkNet(joinPoint: ProceedingJoinPoint): Any? {
        val signature = joinPoint.signature as MethodSignature
        val checkNet = signature.method.getAnnotation(NetCheck::class.java)
        if (checkNet != null) {
            // 判断有没有网络,获取context
            //View Activity Fragment,getThis()获得当前切点方法所在的类
            val any = joinPoint.`this`//
            val context = getContext(any)
            if (context != null) {
                if (!context.isNetworkAvailable()) {
                    context.showToast(R.string.no_net_tip)
                    return null
                }
            }
        }
        return joinPoint.proceed()
    }

    /**
     * 通过对象获取上下文
     */
    private fun getContext(any: Any): Context? =
            when (any) {
                is Activity -> any
                is Fragment -> {
                    any.activity
                }
                is View -> {
                    any.context
                }
                else -> null
            }
}