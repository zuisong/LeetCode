package cn.mmooo

import java.util.*

fun main(args: Array<String>) {

    val express = "5+2*(8-2)-1".toCharArray()
    val i = caculate(express)
    println(i)
}

fun caculate(express: CharArray): Int {
    TODO("no implete")

    val map = mapOf('+' to 1,'-' to 1,'*' to 2,'(' to 10)

    val stack = Stack<Char>()
}