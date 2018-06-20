package cn.mmooo.q11

class Solution {
    // 递归解法，递归太深导致栈溢出
    fun maxArea1(height: IntArray): Int {
        fun maxAreaOfIndex(startIndex: Int, endIndex: Int): Int {
            val currentArea = (endIndex - startIndex) * Math.min(height[startIndex], height[endIndex])
            if (endIndex - startIndex <= 1) {
                return currentArea
            }

            return when {
                height[startIndex] > height[endIndex] -> {
                    Math.max(currentArea, maxAreaOfIndex(startIndex, endIndex - 1))
                }
                height[startIndex] < height[endIndex] -> {
                    Math.max(currentArea, maxAreaOfIndex(startIndex + 1, endIndex))
                }
                else -> {
                    Math.max(currentArea, maxAreaOfIndex(startIndex + 1, endIndex - 1))
                }
            }

        }
        return maxAreaOfIndex(0, height.lastIndex)
    }

    // 双指针解法
    fun maxArea(height: IntArray): Int {
        var maxArea = 0

        var startIndex = 0
        var endIndex = height.lastIndex

        while (endIndex - startIndex > 0) {
            val currentArea = (endIndex - startIndex) * Math.min(height[startIndex], height[endIndex])
            if (currentArea > maxArea) maxArea = currentArea


            when {
                height[startIndex] > height[endIndex] -> {
                    endIndex--
                }
                height[startIndex] < height[endIndex] -> {
                    startIndex++
                }
                else -> {
                    startIndex++
                    endIndex--
                }
            }

        }
        return maxArea

    }
}


fun main(args: Array<String>) {
    val intArray =
            """
            9384,887,2778,6916,7794,8336,5387,
            493,6650,1422,2363,28,8691,60,7764,3927,541,3427,9173,5737,5212,
            5369,2568,6430,5783,1531,2863,5124,4068,3136,3930,9803,4023,3059,3070,8168,1394,8457,
            5012,8043,6230,7374,4422,4920,3785,8538,5199,4325,8316,4371,6414,3527,6092,8981,9957,1874,
            6863,9171,6997,7282,2306,926,7085,6328,337,6506,847,1730,1314,5858,6125,3896,9583,546,8815,3368
            """
                    .split(",").map { it.trim() }.map { it.toInt() }.toIntArray()
    Solution().maxArea(intArray).let(::println)
}