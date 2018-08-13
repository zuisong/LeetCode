package cn.mmooo.q15

import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val ints = intArrayOf(3, 6, 8, 7, 4, -5, -12, -9, -10, 56, -11)
    measureTimeMillis {
        threeSum(ints).let(::println)
    }.let(::println)
}

fun threeSum(nums: IntArray): List<List<Int>> {
    val map = nums
            .withIndex()
            .groupBy { it.value }
            .mapValues { it.value.map { t -> t.index }.max() }
            .toMap()
    var list = arrayListOf<List<Int>>()
    (0..nums.lastIndex).forEach { i ->
        (i + 1..nums.lastIndex).forEach { j ->
            val i1 = 0 - nums[i] - nums[j]
            if (map[i1] ?: -1 > j) {
                list.add(element = listOf(nums[i], nums[j], i1).sorted())
            }
        }
    }
    return list.distinct()
}
