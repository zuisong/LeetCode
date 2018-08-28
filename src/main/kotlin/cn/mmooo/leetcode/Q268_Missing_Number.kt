package cn.mmooo.leetcode


fun main(args: Array<String>) {
    missingNumber(intArrayOf(0, 1)).let(::println)
}

/**
给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。
输入: [9,6,4,2,3,5,7,0,1]
输出: 8
 */
fun missingNumber(nums: IntArray): Int {

    for (i in 0..nums.lastIndex) {
        var v = nums[i]
        while ((v != i) and (v < nums.size)) {
            nums[i] = nums[v]
            nums[v] = v
            v = nums[i]
        }
    }


    return nums.indexOf(nums.size).takeUnless { it == -1 } ?: nums.size
}
