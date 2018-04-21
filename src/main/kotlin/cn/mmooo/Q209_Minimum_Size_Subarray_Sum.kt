package cn.mmooo.q209

/**
尺取法：顾名思义，像尺子一样取一段，借用挑战书上面的话说，尺取法通常是对数组保存一对下标，即所选取的区间的左右端点，
然后根据实际情况不断地推进区间左右端点以得出答案。之所以需要掌握这个技巧，是因为尺取法比直接暴力枚举区间效率高很多，
尤其是数据量大的。

那么，用”尺取法“做上面这道题思路应该是这样的：

其实，这种方法很类似于蚯蚓的蠕动。

1）用一对脚标i, j。最开始都指向第一个元素。

2）如果区间i到j之和比s小，就让j往后挪一位，并把sum的值加上这个新元素。相当于蚯蚓的头向前伸了一下。

3）如果区间i到j之和比s大，就让sum减掉第一个元素。相当于蚯蚓的尾巴向前缩了一下。

4）如果i到j之和刚好等于s，则输入。

用一张图来表示就是这样的，每一行的黄色部分代表本次循环选中的区间
 */
fun main(args: Array<String>) {
    Solution().minSubArrayLen(6, intArrayOf(10, 2, 3)).let(::println)
}

class Solution {
    fun minSubArrayLen(k: Int, nums: IntArray): Int {
        if (nums.isEmpty()) {
            return 0
        }
        var minLen = Int.MAX_VALUE
        var s = 0
        var e = 0
        var sum = nums[s]


        loop@ while (true) {
            when {
                sum >= k -> {
                    minLen = Math.min(e - s + 1, minLen)
                    s += 1
                    if (s == nums.lastIndex) {
                        break@loop
                    }
                    sum -= nums[s - 1]
                }
                sum < k -> {
                    e += 1
                    if (e > nums.lastIndex) {
                        break@loop
                    }
                    sum += nums[e]
                }
            }
            if (s > e) {
                sum = nums[s]
                e = s
            }
        }
        return if (minLen == Int.MAX_VALUE) 0 else minLen

    }
}