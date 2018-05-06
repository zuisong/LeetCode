package cn.mmooo.q633

fun main(args: Array<String>) {

Solution().judgeSquareSum(4).let(::println)

}

class Solution {
// 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c。
    fun judgeSquareSum(num: Int): Boolean {
        val sqrt = Math.sqrt(num.toDouble())
        var i = 0
        var j = sqrt.toInt() + 1

        while (i <= j) {
            val square = i * i + j * j
            when {
                square > num -> j--
                square < num -> i++
                else -> {
                    return true
                }
            }
        }
        return false
    }
}
