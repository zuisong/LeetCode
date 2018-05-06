package cn.mmooo.q367

/**
给定一个正整数 num，编写一个函数，如果 num 是一个完全平方数，则返回 True，否则返回 False。

注意：不要使用任何内置的库函数，如  sqrt。
 */
fun main(args: Array<String>) {
    Solution().isPerfectSquare(2147483647).let(::println)
}

class Solution {
    fun isPerfectSquare(num: Int): Boolean {
        var l = 0L
        var h = num.toLong()
        while (l <= h) {
            val mid = l + (h - l) / 2
            var square = mid * mid
            when {
                square > num -> h = mid - 1
                square < num -> l = mid + 1
                else -> return true
            }
        }
        return false
    }
}