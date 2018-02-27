package com.tufei.mvvmkotlin

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import javax.inject.Provider

/**
 * @author tufei
 * @date 2018/2/26.
 */
@RunWith(RobolectricTestRunner::class)
class ViewModelFactoryTest {
    private lateinit var creators: Map<Class<out ViewModel>, Provider<ViewModel>>
    private lateinit var provider: Factory
    private lateinit var viewModelFactory: ViewModelFactory

    class TestViewModel(context: Application) : AndroidViewModel(context)

    class NotIncludedViewModel(context: Application) : AndroidViewModel(context)

    class Factory(private val context: Application) : Provider<ViewModel> {
        override fun get(): TestViewModel = TestViewModel(context)
    }

    @Before
    fun setup() {
        provider = Factory(RuntimeEnvironment.application)
        creators = mapOf(TestViewModel::class.java to provider)
        viewModelFactory = ViewModelFactory(creators)
    }

    @Test
    fun create() {
        val viewModel = viewModelFactory.create(TestViewModel::class.java)
        assertEquals(provider.get().javaClass, viewModel.javaClass)
    }

    @Test(expected = IllegalArgumentException::class)
    fun createWhenMapWithout() {
        viewModelFactory.create(NotIncludedViewModel::class.java)
    }

}