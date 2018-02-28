package com.tufei.mvvmkotlin.testutil

/**
 * @author tufei
 * @date 2018/2/28.
 */
/**
 * Exception for testing if unchecked expections propagate as-is without confusing with
 * other type of common exceptions.
 */
class TestException : RuntimeException {

    /**
     * Constructs a TestException without message or cause.
     */
    constructor() : super()

    /**
     * Counstructs a TestException with message and cause.
     * @param message the message
     * @param cause the cause
     */
    constructor(message: String, cause: Throwable) : super(message, cause)

    /**
     * Constructs a TestException with a message only.
     * @param message the message
     */
    constructor(message: String) : super(message)

    /**
     * Constructs a TestException with a cause only.
     * @param cause the cause
     */
    constructor(cause: Throwable) : super(cause)

    companion object {

        private val serialVersionUID = -1438148770465406172L
    }


}