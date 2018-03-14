package com.tufei.mvvmkotlin.db

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import com.tufei.mvvmkotlin.testutil.RobolectricTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.robolectric.RuntimeEnvironment
import java.util.*

/**
 * @author tufei
 * @date 2018/3/14.
 */
class PersonsLocalDataSourceTest : RobolectricTest() {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val tony = "Tony"
    private val marry = "Marry"
    private val tom = "Tom"

    private lateinit var database: PersonDatabase
    private lateinit var localDataSource: PersonsLocalDataSource

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(RuntimeEnvironment.application,
                PersonDatabase::class.java)
                .allowMainThreadQueries()
                .build()
        localDataSource = PersonsLocalDataSource(database.personDao())
    }

    @After
    fun clear() {
        database.close()
    }

    @Test
    fun savePerson_retrievesPerson() {
        val id = UUID.randomUUID().toString()
        val person = Person(tony, Sex.MALE, id)
        localDataSource.savePerson(person)
                .test()
                .assertNoErrors()
                .assertComplete()
        localDataSource.getPerson(id)
                .test()
                .assertNoErrors()
                .assertValue {
                    it == person
                }
    }
}