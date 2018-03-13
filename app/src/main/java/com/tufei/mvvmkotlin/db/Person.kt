package com.tufei.mvvmkotlin.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

/**
 * @author tufei
 * @date 2018/3/10.
 */
enum class Sex{
    MALE,FEMALE
}

@Entity(tableName = "persons")
data class Person constructor(
        @ColumnInfo var name: String,
        @ColumnInfo var sex: Sex,
        @PrimaryKey @ColumnInfo(name = "entryid") var id: String = UUID.randomUUID().toString()
)