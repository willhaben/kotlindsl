package com.minki.repl

object Write {
    val words = listOf("Write").toMutableList()

    override fun toString() = words.joinToString(" ")
}

object like {
    override fun toString() = "like"
}

object poem {
    override fun toString() = "poem"
}


infix fun Write.code(like : like) : Write{
    words.add("code")
    words.add(like.toString())
    return this
}

infix fun Write.a(poem : poem) : Write{
    words.add("a")
    words.add(poem.toString())
    return this
}

fun main() {
    println(Write code like a poem)
}