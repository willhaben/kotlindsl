package com.minki.dslui

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk21.listeners.onClick

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        verticalLayout {
            padding = dip(16)

            val name = editText()

            button("Say Hello") {
                onClick { toast("Hello, ${name.text}!") }
            }
        }
    }
}
