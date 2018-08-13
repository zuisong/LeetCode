package cn.mmooo.q53


/**
Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
the contiguous subarray [4,-1,2,1] has the largest sum = 6.
 */
fun main(args: Array<String>) {
    maxSubArray(intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4))
            .let { println("最优时间复杂度算法的解是: $it") }
    maxSubArray2(intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4))
            .let { println("分治思想实现的解是: $it") }

}

/**
 * 最大子序列和问题: 从一个数组找出连续的一串，使得这一串的和是所有字串中最大的
 */
/**
 * O(n)复杂度算法，最优解
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
 * 分治算法
 * 分成左右两块求解，最大的子序列和肯定出现在 左边、右边或者在穿越左右两块的一个子串
 * 在左右两边的相当于第一个问题的一个子问题，重点在于求跨越两边的最大子串
 * 看代码
 */
fun maxSubArray2(nums: IntArray, startIndex: Int = 0, endIndex: Int = nums.lastIndex): Int {
    if (endIndex <= startIndex) {
        return nums[startIndex]
    }

    val mid = (startIndex + endIndex) / 2


    var i = mid
    var maxOfLeft = 0
    var sumOfLeft = 0
    while (i >= startIndex) {
        sumOfLeft += nums[i]
        if (sumOfLeft > maxOfLeft) {
            maxOfLeft = sumOfLeft
        }
        i--
    }
    var maxOfRight = 0
    var sumOfRight = 0
    var j = mid + 1
    while (j <= endIndex) {
        sumOfRight += nums[j]
        if (sumOfRight > maxOfRight) {
            maxOfRight = sumOfRight
        }
        j++
    }
    var maxCrossMid = maxOfLeft + maxOfRight

    return maxOf(
            maxSubArray2(nums, startIndex, mid - 1),
            maxSubArray2(nums, mid + 1, endIndex),
            maxCrossMid)
}


