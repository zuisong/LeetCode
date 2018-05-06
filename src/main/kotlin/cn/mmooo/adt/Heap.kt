package cn.mmooo.adt

import java.util.*


class SimpleMinBinaryHeapImpl<E : Comparable<E>>(initSize: Int = 10) : BinaryHeap<E>, Iterable<E> {
    override fun iterator(): Iterator<E> {
        return object : Iterator<E> {
            private var iterIdx = 0
            override fun hasNext(): Boolean {
                return iterIdx < size
            }

            override fun next(): E {
                val e = arr[iterIdx]!!
                iterIdx++
                return e
            }
        }
    }

    var arr: Array<E?>
    var size: Int

    init {
        size = 0
        arr = Array<Comparable<*>?>(size = initSize) { null } as Array<E?>
    }


    private fun enlargeArray(newSize: Int = 2 * size) {
        arr = arr.copyOf(newSize)
    }

    override fun getMin(): E {
        checkEmpty()
        return arr.first()!!
    }

    fun checkEmpty() {
        if (size == 0) {
            throw  IllegalAccessError("")
        }
    }

    override fun removeMin(): E {
        checkEmpty()

        val e = arr.first()!!
        arr[0] = arr[size - 1]
        size--

        var idx = 0
        while (idx.left < size) {
            val minIdx = when {
                idx.right >= size -> idx.left
                arr[idx.left]!! <= arr[idx.right]!! -> idx.left
                else -> idx.right
            }
            arr.swap(idx, minIdx)
            idx = minIdx
        }
        return e
    }

    override fun insert(x: E) {
        if (size >= arr.size)
            enlargeArray()
        arr[size] = x
        var idx = size
        while (arr[idx.parent]!! > x) {
            arr.swap(idx, idx.parent)
            idx = idx.parent
        }

        this.size++
    }

    override fun size(): Int {
        return size
    }

    companion object {

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
    val heap = SimpleMinBinaryHeapImpl<Int>(50)
    val random = Random()
    repeat(20) {
        heap.insert(random.nextInt(500))
    }
    heap.min().let(::println)
    heap.forEach(::println)

}
