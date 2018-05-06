package cn.mmooo.adt

interface BinaryTree<E : Comparable<E>> {
    fun isEmpty(): Boolean
    fun makeEmpty()
    fun insert(ele: E)
    fun findMax(): E
    fun findMin(): E
    fun remove(x: E)
    operator fun contains(ele: E): Boolean
}