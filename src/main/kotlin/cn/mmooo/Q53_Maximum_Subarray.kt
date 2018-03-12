package cn.mmooo.q53

private class Solution {
    fun maxSubArray(nums: IntArray): Int {

        val max = nums.max()!!
        if (max <= 0) return max
        var maxSum = 0

        var sum = 0

        nums.forEach {
            sum += it
            if (sum < 0) {
                sum = 0
            }
            if (sum > maxSum) {
                maxSum = sum
            }

        }
        return maxSum
    }

    fun maxSubArray2(nums: IntArray): Int {

        val max = nums.max()!!
        if (max <= 0) return max
        var maxSum = 0

        var sum = 0

        nums.forEach {
            sum += it
            if (sum < 0) {
                sum = 0
            }
            if (sum > maxSum) {
                maxSum = sum
            }

        }
        return maxSum
    }


}

/**
Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
the contiguous subarray [4,-1,2,1] has the largest sum = 6.
 */
fun main(args: Array<String>) {
    Solution().maxSubArray(intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4))
            .let { println(it) }

}