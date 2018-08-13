package cn.mmooo.q41

import java.util.*

/**
给定一个未排序的整数数组，找出其中没有出现的最小的正整数。

示例 1:

输入: [1,2,0]
输出: 3
示例 2:

输入: [3,4,-1,1]
输出: 2
示例 3:

输入: [7,8,9,11,12]
输出: 1
说明:

你的算法的时间复杂度应为O(n)，并且只能使用常数级别的空间。
 */

fun main(args: Array<String>) {
    val random = Random()
    val nums = IntArray(1000, { random.nextInt(500) }).distinct().toIntArray()
    firstMissingPositive(nums)
            .let(::println)
    println(nums.toList().sorted().slice(0..30))
}


fun firstMissingPositive(nums: IntArray): Int {
    var tempValue: Int
    repeat(nums.size) { idx ->
        var d = nums[idx]
        while (d > 0 && d < nums.size) {
            tempValue = nums[d - 1]
            if (tempValue == d) {
                break
            }
            nums[d - 1] = d
            d = tempValue
            nums[idx] = tempValue
        }
    }
    return 1 + (nums.withIndex().firstOrNull { it.index != it.value - 1 }?.index ?: nums.size)
}
