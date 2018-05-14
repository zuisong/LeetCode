package cn.mmooo.adt

interface Stack<E> {
    fun peekLast(): E
    fun pop(): E
    fun push(e: E)
    fun size(): Int
    fun makeEmpty()
    fun isEmpty(): Boolean
}