package cn.mmooo.q283

/**
Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].

Note:
You must do this in-place without making a copy of the array.
Minimize the total number of operations.
 */
fun main(args: Array<String>) {

    val arr = intArrayOf(0, 1, 0, 3, 12)
    Solution().moveZeroes(arr)
    print(arr.toList())

}


class Solution {
    fun moveZeroes(nums: IntArray): Unit {
        (1 until nums.size).forEach { i ->
            var j = i
            val tempValue = nums[i]
            while (j > 0 && nums[j - 1] == 0) {
                nums[j] = nums[j - 1]
                j--
            }
            nums[j] = tempValue
        }
    }
}