package com.tufei.mvvmkotlin.db

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.filters.LargeTest
import android.support.test.runner.AndroidJUnit4
import com.tufei.mvvmkotlin.util.rx.RxJava
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

/**
 * @author tufei
 * @date 2018/3/13.
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class PersonsLocalDataSourceTest {
    val tony = "Tony"
    val marry = "Marry"
    val tom = "Tom"

    private lateinit var database: PersonDatabase
    private lateinit var localDataSource: PersonsLocalDataSource

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                PersonDatabase::class.java)
                .build()
        RxJava.asyncToSync()
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

    @Test
    fun getPerson() {
    }

    @Test
    fun savePerson() {
    }

    @Test
    fun deletePersons() {
    }

    @Test
    fun deletePerson() {
    }

}