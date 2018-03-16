package cn.mmooo.sort

fun main(args: Array<String>) {
    val nums = intArrayOf(1, 1545, 5, 7, 8, 32, 8, 431, 432, 53, 445, 12, 45).toTypedArray()
    quickSort(nums)
    println(nums.toList())
}

private fun <T> Array<T>.swap(i1: Int, i2: Int) {
    val t = this[i1]
    this[i1] = this[i2]
    this[i2] = t
}

fun <T : Comparable<T>> quickSort(arr: Array<T>, i1: Int = 0, i2: Int = arr.lastIndex) {

    if (i1 >= i2) {
        return
    }

    var i = i1
    var j = i2 - 1

    while (i <= j) {
        if (arr[i] <= arr[i2]) {
            i++
            continue
        }
        if (arr[j] > arr[i2]) {
            j--
            continue
        }
        arr.swap(i, j)
    }

    arr.swap(i2, i)
    quickSort(arr, i1, i - 1)
    quickSort(arr, i + 1, i2)
}