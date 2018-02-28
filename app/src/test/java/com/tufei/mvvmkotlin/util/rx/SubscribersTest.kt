package com.tufei.mvvmkotlin.util.rx

import com.tufei.mvvmkotlin.testutil.RobolectricRule
import com.tufei.mvvmkotlin.testutil.TestException
import io.reactivex.Maybe
import io.reactivex.internal.operators.completable.CompletableFromAction
import io.reactivex.rxkotlin.toFlowable
import io.reactivex.rxkotlin.toObservable
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * @author tufei
 * @date 2018/2/28.
 */
@RunWith(RobolectricTestRunner::class)
class SubscribersTest {
    @get:Rule
    val robolectricRule = RobolectricRule()


    @Test
    fun intoForSingle() {
        val observable = "abc"
        var actual = ""
        observable.toSingle()
                .into {
                    actual = it
                }
        assertEquals(observable, actual)
    }

    @Test
    fun intoForObservable() {
        val actual = mutableListOf<String>()
        arrayListOf("a", "b", "c").toObservable()
                .into {
                    actual.add(it)
                }
        assertEquals(arrayListOf("a", "b", "c"), actual)
    }

    @Test
    fun intoForFlowable() {
        val actual = mutableListOf<String>()
        arrayListOf("a", "b", "c").toFlowable()
                .into {
                    actual.add(it)
                }
        assertEquals(arrayListOf("a", "b", "c"), actual)
    }

    @Test
    fun intoForMaybe() {
        var actual = 0
        Maybe.just(1)
                .into {
                    actual = it
                }
        assertEquals(1, actual)
    }

    @Test
    fun intoForCompletable() {
        var actual = Throwable()
        CompletableFromAction {
            throw TestException()
        }.into(onError = {
            actual = it
        })
        assertEquals(TestException::class.java, actual.javaClass)
    }
}