package cn.mmooo.adt

interface Stack<E> : Collection<E> {
    fun peekLast(): E
    fun pop(): E
    fun push(e: E)
    fun makeEmpty()
}