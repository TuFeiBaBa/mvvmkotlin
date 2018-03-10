package com.tufei.mvvmkotlin.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

/**
 * @author tufei
 * @date 2018/3/10.
 */
@Database(entities = [Person::class], version = 1)
abstract class PersonDatabase : RoomDatabase() {

    abstract fun personDao(): PersonsDao

    companion object {

        private var INSTANCE: PersonDatabase? = null

        private val lock = Any()

        fun getInstance(context: Context): PersonDatabase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            PersonDatabase::class.java, "persons.db")
                            .build()
                }
                return INSTANCE!!
            }
        }
    }
}