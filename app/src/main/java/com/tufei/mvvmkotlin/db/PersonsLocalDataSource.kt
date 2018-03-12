/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tufei.mvvmkotlin.db

import io.reactivex.Completable
import io.reactivex.internal.operators.completable.CompletableFromAction
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Concrete implementation of a data source as a db.
 */
@Singleton
class PersonsLocalDataSource @Inject constructor(
        private val personsDao: PersonsDao
) : PersonsDataSource {
    override fun getPersons() = personsDao.getPersons()

    override fun getPerson(personId: String) =
            personsDao.getPersonById(personId)

    override fun savePerson(person: Person): Completable =
            CompletableFromAction {
                personsDao.insertPersion(person)
            }

    override fun deletePersons(): Completable =
            CompletableFromAction{
                personsDao.deletePersons()
            }

    override fun deletePerson(taskId: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}