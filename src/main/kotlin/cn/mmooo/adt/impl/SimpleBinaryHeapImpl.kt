package cn.mmooo.adt.impl

import cn.mmooo.adt.BinaryHeap
import java.util.*


class SimpleBinaryHeapImpl<E : Comparable<E>>(private val heapType: BinaryHeap.HeapType, initSize: Int = 10) : BinaryHeap<E> {
    override val size: Int
        get() = currentSize

    override fun contains(element: E): Boolean =
            this.stream().anyMatch { it == element }


    override fun containsAll(elements: Collection<E>): Boolean = elements.parallelStream().allMatch(this::contains)

    override fun makeEmpty() {
        this.currentSize = 0
    }

    override fun isEmpty(): Boolean {
        return this.currentSize == 0
    }

    override fun getTopOrNull(): E? {
        return arr.firstOrNull()
    }

    override fun getTopOr(default: E): E {
        return arr.firstOrNull() ?: default
    }

    private fun compare(e1: E, e2: E): Int {
        val compareTo = e1.compareTo(e2)
        return if (heapType == BinaryHeap.HeapType.MAX_HEAP) {
            compareTo
        } else {
            -1 * compareTo
        }
    }

    override fun iterator(): Iterator<E> {
        return object : Iterator<E> {
            private var iterIdx = 0
            override fun hasNext(): Boolean {
                return iterIdx < currentSize
            }

            override fun next(): E {
                val e = arr[iterIdx]!!
                iterIdx++
                return e
            }
        }
    }

    private var arr: Array<E?>
    private var currentSize: Int = 0

    init {
        this.arr = Array<Comparable<*>?>(size = initSize) { null } as Array<E?>
    }

    private fun enlargeArray(newSize: Int = 2 * currentSize) {
        arr = arr.copyOf(newSize)
    }

    override fun getTop(): E {
        checkEmpty()
        return arr.first()!!
    }

    private fun checkEmpty() {
        if (currentSize == 0) {
            throw  IllegalAccessError("")
        }
    }

    override fun removeTop(): E {
        checkEmpty()
        val e = arr.first()!!
        arr[0] = arr[currentSize - 1]
        currentSize--
        var index = 0
        pollDown(index)
        return e
    }

    override fun insert(x: E) {
        if (currentSize >= arr.size)
            enlargeArray()
        arr[currentSize] = x
        var index = currentSize
        this.currentSize++
        pollUp(index)

    }

    private fun pollUp(idx: Int) {
        if (compare(arr[idx.parent]!!, arr[idx]!!) < 0) {
            arr.swap(idx, idx.parent)
            pollUp(idx.parent)
        }
    }

    private fun pollDown(idx: Int) {
        if (idx.left > currentSize) return
        val toUpIdx = when {
            idx.right >= currentSize -> idx.left
            compare(arr[idx.left]!!, arr[idx.right]!!) >= 0 -> idx.left
            else -> idx.right
        }
        if (compare(arr[idx]!!, arr[toUpIdx]!!) < 0) {
            arr.swap(idx, toUpIdx)
            pollDown(toUpIdx)
        }
    }


    private companion object {

        val Int.left get() = 2 * this + 1
        val Int.right get() = 2 * this + 2
        val Int.parent get() = (this - 1) / 2

        fun <T> Array<T>.swap(i1: Int, i2: Int) {
            val t = this[i1]
            this[i1] = this[i2]
            this[i2] = t
        }
    }
}

fun main(args: Array<String>) {
    val heap = SimpleBinaryHeapImpl<Int>(BinaryHeap.HeapType.MIN_HEAP, 50)
    val random = Random()
    repeat(20) {
        heap.insert(random.nextInt(500))
    }
    for (i in 1..20) {
        val top: Int = heap.removeTop()
        println(top)
    }

}
