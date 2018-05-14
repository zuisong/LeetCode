package cn.mmooo.adt

interface Queue<E> {
    fun peekFirst(): E
    fun poll(): E
    fun push(e: E)
    fun size(): Int
    fun makeEmpty()
    fun isEmpty(): Boolean
}