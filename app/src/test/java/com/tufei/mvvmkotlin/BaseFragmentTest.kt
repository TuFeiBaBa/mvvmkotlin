package com.tufei.mvvmkotlin

import com.tufei.mvvmkotlin.di.FragmentScoped
import com.tufei.mvvmkotlin.testutil.RobolectricTest
import dagger.Module
import dagger.android.ContributesAndroidInjector
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.RuntimeEnvironment
import org.robolectric.shadows.ShadowApplication
import org.robolectric.shadows.ShadowToast
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil.startFragment
import javax.inject.Inject

/**
 * @author TuFei
 * @date 2018/3/29.
 */
class BaseFragmentTest : RobolectricTest() {
    lateinit var testFragment: TestFragment

    inner class TestFragment @Inject constructor() : BaseFragment<BaseViewModel>() {
        override fun setViewModel() = viewModel
        override fun setContentView() = R.layout.abc_activity_chooser_view
    }

    @Before
    fun setup() {
        val testFragment = TestFragment()
        startFragment(testFragment)
    }

    @Test
    fun testShowToast() {
//        viewModel.toastTip.set("Hello,world！")
//        val latestToast = ShadowToast.getTextOfLatestToast()
//        assertEquals("Hello,world!", latestToast)
    }
}