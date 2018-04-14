package com.tufei.mvvmkotlin.aop

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.view.View
import com.tufei.mvvmkotlin.util.showToast
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature

/**
 * @author tufei
 * @date 2018/3/4.
 */
@Aspect
class ClickAspect {
    private var lastClickTime = 0L
    private var lastTag = 0

    @Pointcut("execution(@com.tufei.mvvmkotlin.aop.ClickCheck * *(..))")
    fun checkClickBehavior() {
    }

    @Around("checkClickBehavior()")
    @Throws(Throwable::class)
    fun checkClick(joinPoint: ProceedingJoinPoint): Any? {
        val signature = joinPoint.signature as MethodSignature
        val clickCheck = signature.method.getAnnotation(ClickCheck::class.java)
        val isDuplicate = isDuplicateClick(clickCheck.interval)
        lastClickTime = System.currentTimeMillis()
        val tipRes = clickCheck.tipRes
        val tag = clickCheck.tag
        val isSame = tag == lastTag
        lastTag = tag
        if (isDuplicate && isSame) {
            if (tipRes != 0) {
                val context = getContext(joinPoint.`this`)
                context?.showToast(tipRes)
            }
            return null
        }
        return joinPoint.proceed()
    }

    private fun isDuplicateClick(interval: Int): Boolean {
        return System.currentTimeMillis() - lastClickTime < interval
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