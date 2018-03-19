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
class PersonDaoTest : RobolectricTest() {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val tony = Person("tony", Sex.MALE, "1")
    private val marry = Person("marry", Sex.FEMALE, "2")

    private lateinit var database: PersonDatabase
    private lateinit var personsDao: PersonsDao

    @Before
    fun setup() {
        // using an in-memory database because the information stored here disappears when the
        // process is killed
        database = Room.inMemoryDatabaseBuilder(RuntimeEnvironment.application,
                PersonDatabase::class.java)
                // allowing main thread queries, just for testing
                .allowMainThreadQueries()
                .build()
        personsDao = database.personDao()
    }

    @After
    fun clear() {
        database.close()
    }

    @Test
    fun savePerson_getPerson() {
        personsDao.insertPersion(tony)
        personsDao.getPersonById("1")
                .test()
                .assertNoErrors()
                //不会完成。因为room是响应式的，会继续观察数据库的数据变化。
                .assertNotComplete()
                .assertValue {
                    tony == it
                }
    }

    @Test
    fun savePersons_getPersons() {
        personsDao.insertPersion(tony)
        personsDao.insertPersion(marry)

        val persons = arrayListOf(tony, marry)
        personsDao.getPersons()
                .test()
                .assertNoErrors()
                .assertNotComplete()
                .assertValue {
                    persons == it
                }
    }

    @Test
    fun savePerson_deletePerson() {
        personsDao.insertPersion(tony)
        personsDao.deletePersonById("1")

        personsDao.getPersonById("1")
                .test()
                .assertNoErrors()
                .assertNotComplete()
                .assertNoValues()
    }

    @Test
    fun savePersons_deletePersons() {
        personsDao.insertPersion(tony)
        personsDao.insertPersion(marry)

        personsDao.deletePersons()

        personsDao.getPersons()
                .test()
                .assertValue {
                    it.isEmpty()
                }
    }
}
