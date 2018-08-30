package cn.mmooo.leetcode

/**
给定一个包含非负数的数组和一个目标整数 k，编写一个函数来判断该数组是否含有连续的子数组，其大小至少为 2，总和为 k 的倍数，
即总和为 n*k，其中 n 也是一个整数。

示例 1:

输入: [23,2,4,6,7], k = 6
输出: True
解释: [2,4] 是一个大小为 2 的子数组，并且和为 6。
示例 2:

输入: [23,2,6,4,7], k = 6
输出: True
解释: [23,2,6,4,7]是大小为 5 的子数组，并且和为 42。
说明:

数组的长度不会超过10,000。
你可以认为所有数字总和在 32 位有符号整数范围内。
 */
/*
参考思路：
在讨论里有个大神给出了时间复杂度是O(n)的解法，他的思路非常巧妙，用了数学上的知识，下面给出他的解法的原理：
假设:
a[i]+a[i+1]+...+a[j]=n1k+q;

如果存在一个n
n>j且a[i]+a[i+1]+...+a[j]+...+a[n]=n2k+q;

那么
a[j+1]+...+a[n]=(n2−n1)k

因此利用这一结果，可以从序列第一个元素开始遍历，不断累加上当前的元素，并求出当前和除以k后的余数，
用一个映射记录该余数出现时的下标，如果同一个余数出现了两次，并且两次出现的下标之差大于1，那么就
表示在这两个坐标之间的元素之和是k的倍数，因此就可以返回true，否则最后返回false。
 */
fun checkSubarraySum(nums: IntArray, k: Int): Boolean {

    val map = hashMapOf<Int, Int>()
    var sum = 0
    map[0] = -1
    for ((i, v) in nums.withIndex()) {
        sum += v
        val rem = sum % k
        if (rem in map) {
            if (i - map[rem]!! > 1) {
                return true
            }
        } else {
            map[rem] = i
        }
    }
    return false
}

fun main(args: Array<String>) {

    checkSubarraySum(intArrayOf(23, 2, 6, 4, 7), 6)
            .let(::println)
    generateSequence(1) { it + 1 }
            .take(10).toList().let(::println)

}