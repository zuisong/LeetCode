package cn.mmooo.adt

interface Queue<E> : Collection<E> {
    fun peekFirst(): E
    fun poll(): E
    fun push(e: E)
    fun makeEmpty()
}