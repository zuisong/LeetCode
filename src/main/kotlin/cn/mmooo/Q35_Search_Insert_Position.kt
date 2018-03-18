package cn.mmooo.q35

private class Solution {
    fun searchInsert(nums: IntArray, target: Int, minIndex: Int = 0, maxIndex: Int = nums.lastIndex): Int {
        return when {
            nums[minIndex] >= target -> minIndex
            nums[maxIndex] == target -> maxIndex
            nums[maxIndex] < target -> maxIndex + 1
            minIndex + 1 == maxIndex -> minIndex + 1
            else -> {
                val mid = (maxIndex + minIndex) / 2
                when {
                    target == nums[mid] -> mid
                    target > nums[mid] -> searchInsert(nums, target, mid, maxIndex)
                    else -> searchInsert(nums, target, minIndex, mid)
                }
            }
        }
    }
}

/**
Given a sorted array and a target value, return the index if the target is found.
If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.
 */
fun main(args: Array<String>) {
    Solution().searchInsert(intArrayOf(1, 3, 5, 6), 0)
            .let {
                println(it)
            }
}