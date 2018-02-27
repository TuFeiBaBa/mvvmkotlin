package com.tufei.mvvmkotlin.adapter

/**
 * @author tufei
 * @date 2018/2/27.
 */
fun main(args: Array<String>) {
    val person = Person()
    person.name = "a"
    person.name = "b"
}

class Person {
    var name: String?=null
        set(value) {
            println(field)
            println(value)
            field = value
        }
}