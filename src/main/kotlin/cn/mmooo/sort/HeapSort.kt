package cn.mmooo.sort

fun main(args: Array<String>) {
    val nums = intArrayOf(1, 5, 7, 8, 32, 431, 432, 53, 12, 45).toTypedArray()
    heapSort(nums)
    println(nums.toList())
}


internal fun <T : Comparable<T>> heapSort(arr: Array<T>) {
    ((arr.size - 1) / 2 downTo 0).forEach { i ->
        makeHeap(arr, i)
    }

    (arr.size - 1 downTo 0).forEach {
        arr.swap(0, it)
        makeHeap(arr, 0, it - 1)
    }
}

private inline val Int.left get() = 2 * this + 1
private inline val Int.right get() = 2 * this + 2

private fun <T> Array<T>.swap(i1: Int, i2: Int) {
    val t = this[i1]
    this[i1] = this[i2]
    this[i2] = t
}

fun <T : Comparable<T>> makeHeap(arr: Array<T>, index: Int, maxIndex: Int = arr.size - 1) {

    var it = index
    while (it.left <= maxIndex) {
        val maxi = when {
            it.right > maxIndex -> it.left
            arr[it.left] >= arr[it.right] -> it.left
            else -> it.right
        }
        if (arr[maxi] > arr[it]) {
            arr.swap(maxi, it)
            it = maxi
        } else {
            break
        }
    }

}

