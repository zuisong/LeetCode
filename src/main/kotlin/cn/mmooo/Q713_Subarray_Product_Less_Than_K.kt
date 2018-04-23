package cn.mmooo.q713

/**
Your are given an array of positive integers nums.

Count and print the number of (contiguous) subarrays
where the product of all the elements in the subarray
is less than k.

统计一个数组的子集 里，符合子集里所有数的乘积小于k的子集个数
 */

fun main(args: Array<String>) {
    val nums = intArrayOf(10, 5, 2, 6)
    Solution().numSubarrayProductLessThanK(nums, 100).let(::println)
}


class Solution {
    fun numSubarrayProductLessThanK(nums: IntArray, k: Int): Int {

        var productResult = 1

        var left = 0

        var count = 0

        nums.forEachIndexed { i, v ->
            productResult *= v
            while (productResult >= k && left <= nums.lastIndex) {
                productResult /= nums[left]
                left++
            }
            count = count + i - left + 1

        }

        return count
    }
}