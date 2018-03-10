package com.tufei.mvvmkotlin.db

import android.arch.persistence.room.*
import io.reactivex.Flowable

/**
 * Data Access Object for the persons table.
 * @author tufei
 * @date 2018/3/10.
 */
@Dao
interface PersonsDao {

    /**
     * Select all persons from the persons table.
     *
     * @return all persons.
     */
    @Query("SELECT * FROM Persons")
    fun getPersons(): Flowable<List<Person>>

    /**
     * Select a person by id.
     *
     * @param personId the person id.
     * @return the person with personId.
     */
    @Query("SELECT * FROM Persons WHERE entryid = :personId")
    fun getPersonById(personId: String): Flowable<Person>

    /**
     * Insert a person in the database. If the person already exists, replace it.
     *
     * @param person the person to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPersion(person: Person)

    /**
     * Update a person.
     *
     * @param person person to be updated
     * @return the number of persons updated. This should always be 1.
     */
    @Update
    fun updatePerson(person: Person): Int

    /**
     * Update the sex status of a person
     *
     * @param personId    id of the person
     * @param sex status to be updated
     */
    @Query("UPDATE persons SET sex = :sex WHERE entryid = :personId")
    fun updateSex(personId: String, sex: String)

    /**
     * Delete a person by id.
     *
     * @return the number of persons deleted. This should always be 1.
     */
    @Query("DELETE FROM persons WHERE entryid = :personId")
    fun deletePersonById(personId: String): Int


    /**
     * Delete all persons.
     */
    @Query("DELETE FROM persons")
    fun deletePersons()
}