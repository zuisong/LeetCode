package cn.mmooo.sort

fun main(args: Array<String>) {
    var nums = generateSequence(1) { it + 1 }
            .take(11)
            .toList()
            .shuffled()
            .toTypedArray()

    println(nums.toList())
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
    val n = arr[i2]

    while (i <= j) {
        when {
            arr[i] <= n -> i++
            arr[j] > n -> j--
            else -> arr.swap(i, j)
        }
    }

    arr.swap(i2, i)
    quickSort(arr, i1, i - 1)
    quickSort(arr, i + 1, i2)
}