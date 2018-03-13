package com.tufei.mvvmkotlin.db

import android.arch.persistence.room.TypeConverter

/**
 * @author tufei
 * @date 2018/3/13.
 */
class Converters {
    @TypeConverter
    fun intToSex(value: Int) =
            when (value) {
                0 -> Sex.MALE
                1 -> Sex.FEMALE
                else -> throw IllegalArgumentException("Only 0 and 1!")
            }

    @TypeConverter
    fun sexToInt(sex: Sex) =
            when (sex) {
                Sex.MALE -> 0
                Sex.FEMALE -> 1
            }
}