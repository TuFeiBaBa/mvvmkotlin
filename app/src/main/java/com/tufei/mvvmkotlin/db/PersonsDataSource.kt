package com.tufei.mvvmkotlin.db

import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * @author tufei
 * @date 2018/3/10.
 */
interface PersonsDataSource {
    fun getPersons(): Flowable<List<Person>>

    fun getPerson(personId: String): Flowable<Person>

    fun savePerson(person: Person):Completable

    fun deletePersons():Completable

    fun deletePerson(taskId: String):Completable
}