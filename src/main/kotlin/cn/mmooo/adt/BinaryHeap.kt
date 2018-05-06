package cn.mmooo.adt

interface BinaryHeap<Anytype : Comparable<Anytype>> {
    fun getMin(): Anytype
    fun removeMin(): Anytype
    fun insert(x: Anytype)
    fun size(): Int
}