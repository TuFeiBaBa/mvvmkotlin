package com.tufei.mvvmkotlin.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

/**
 * @author tufei
 * @date 2018/3/10.
 */
@Entity(tableName = "persons")
data class Person constructor(
        @ColumnInfo var name: String,
        @ColumnInfo var sex: String,
        @PrimaryKey @ColumnInfo(name = "entryid") var id: String = UUID.randomUUID().toString()
)