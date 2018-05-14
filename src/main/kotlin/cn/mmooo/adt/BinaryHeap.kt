package cn.mmooo.adt

interface BinaryHeap<Anytype : Comparable<Anytype>>:Collection<Anytype> {
    fun getTop(): Anytype
    fun getTopOrNull(): Anytype?
    fun getTopOr(default: Anytype): Anytype
    fun removeTop(): Anytype
    fun insert(x: Anytype)
    fun makeEmpty()
    enum class HeapType {
        MAX_HEAP, MIN_HEAP
    }
}