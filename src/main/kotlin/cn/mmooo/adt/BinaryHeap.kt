package cn.mmooo.adt

interface BinaryHeap<Anytype : Comparable<Anytype>> {
    fun getTop(): Anytype
    fun getTopOrNull(): Anytype?
    fun getTopOr(default: Anytype): Anytype
    fun removeTop(): Anytype
    fun insert(x: Anytype)
    fun size(): Int
    fun makeEmpty()
    fun isEmpty(): Boolean
    enum class HeapType {
        MAX_HEAP, MIN_HEAP
    }
}