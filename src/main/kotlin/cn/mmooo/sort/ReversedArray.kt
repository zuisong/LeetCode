package cn.mmooo.sort

// 反转数组
fun main(args: Array<String>) {
    val nums: Array<Int> = intArrayOf(1, 2, 5, 7, 8, 32, 431, 432, 53, 12, 45).toTypedArray()
    reverse(nums)
    println(nums.toList())
}


private fun <T> Array<T>.swap(i1: Int, i2: Int) {
    val t = this[i1]
    this[i1] = this[i2]
    this[i2] = t
}

fun <T : Comparable<T>> reverse(arr: Array<T>) {

    (1..arr.size / 2).forEach {
        arr.swap(it - 1, arr.size - it)
    }
}
