package com.tufei.mvvmkotlin.db

import com.tufei.mvvmkotlin.testutil.RobolectricTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.robolectric.RuntimeEnvironment

/**
 * @author tufei
 * @date 2018/3/10.
 */
class PersonsLocalDataSourceTest : RobolectricTest() {
    lateinit var dataSource: PersonsLocalDataSource
    @Before
    fun setup() {
        val personDao = PersonDatabase.getInstance(RuntimeEnvironment.application).personDao()
        dataSource = PersonsLocalDataSource(personDao)
    }

    @After
    fun clear() {
        dataSource.deletePersons()
    }

    @Test
    fun getPerson() {
        val person = Person("Tony", "man", "1")
        dataSource.savePerson(person)
                .test()
                .assertNoErrors()

        dataSource.getPerson("1")
                .test()
                .assertNoErrors()
                .assertValue {
                    it == person
                }
    }
}