package cn.mmooo.leetcode

/**
Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.
 */
fun main(args: Array<String>) {
    majorityElement(intArrayOf(2, 3, 3))
            .let { println(it) }
}


fun majorityElement(nums: IntArray): Int {
    return nums.toList().groupingBy { it }.eachCount().maxBy { it.value }.let { it!!.key }
}
