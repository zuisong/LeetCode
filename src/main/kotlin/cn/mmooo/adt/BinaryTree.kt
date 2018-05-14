package cn.mmooo.adt

interface BinaryTree<E : Comparable<E>>:Collection<E> {
    fun makeEmpty()
    fun insert(ele: E)
    fun findMax(): E
    fun findMin(): E
    fun remove(x: E)
}