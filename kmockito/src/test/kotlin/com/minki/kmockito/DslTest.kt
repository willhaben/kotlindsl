package com.minki.kmockito

import com.nhaarman.mockitokotlin2.*
import org.junit.Test

class DslTest {

    @Test
    fun foo() {
        val myInterface = mock<MyInterface> {
            on {
                getText()
            } doReturn "foo"

            on {
                getInt()
            } doReturn 1
        }

        val doer = Doer(myInterface)
        doer()

        verify(myInterface).doSomeThing()
    }
}