package cn.mmooo.q53


/**
Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
the contiguous subarray [4,-1,2,1] has the largest sum = 6.
 */
fun main(args: Array<String>) {
    Solution().maxSubArray(intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4))
            .let { println(it) }

}

/**
 * 最大子序列和问题: 从一个数组找出连续的一串，使得这一串的和是所有字串中最大的
 */
private class Solution {
    /**
     * O(log(n))复杂度算法，最优解
     */
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

    /**
     * 分治解法
     */
    fun maxSubArray2(nums: IntArray): Int {
        TODO("待实现")
    }

}

