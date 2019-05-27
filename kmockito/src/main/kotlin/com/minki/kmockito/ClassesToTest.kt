package com.minki.kmockito

interface MyInterface {
    fun getText() : String
    fun getInt() : Int
    fun doSomeThing()
}

class Doer(private val myInterface: MyInterface) {

    operator fun invoke() {
        if(myInterface.getText() == "foo" && myInterface.getInt() == 1) {
            myInterface.doSomeThing()
        }
    }

}