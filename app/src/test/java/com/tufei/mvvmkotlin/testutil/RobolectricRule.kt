package com.tufei.mvvmkotlin.testutil

import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import org.robolectric.shadows.ShadowLog

/**
 * @author tufei
 * @date 2018/2/20.
 */
class RobolectricRule : TestRule {
    override fun apply(base: Statement, description: Description?): Statement {
        ShadowLog.stream = System.out
        RxJava.asyncToSync()
        return base
    }
}