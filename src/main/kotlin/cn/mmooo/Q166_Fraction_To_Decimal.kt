package cn.mmooo.q166

/**
给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以字符串形式返回小数。

如果小数部分为循环小数，则将循环的部分括在括号内。
 */
fun main(args: Array<String>) {
    val 分数转小数 = Solution()::fractionToDecimal
    分数转小数(-1, 7).let(::println)
    分数转小数(5, 10).let(::println)
}

class Solution {
    fun fractionToDecimal(numerator: Int, denominator: Int): String {
        val num1 = Math.abs(denominator.toLong())
        val num2 = Math.abs(numerator.toLong())
        // numerator / denominator
        val positive = num2 / num1
        var result: String = positive.toString()
        val map = LinkedHashMap<Long, Long>()
        var yushu = num2 % num1
        if (yushu != 0L)
            while (true) {
                if (yushu in map) {
                    // println("循环")
                    val builder = StringBuilder("$positive.")
                    for ((k, v) in map) {
                        if (k == yushu) builder.append("(")
                        builder.append(v)
                    }
                    builder.append(")")
                    result = builder.toString()
                    break
                } else if (yushu == 0L) {
                    //   println("整除")
                    val builder = StringBuilder("$positive.")
                    for ((k, v) in map) {
                        builder.append(v)
                    }
                    result = builder.toString()
                    break
                } else {

                    map.set(yushu, yushu * 10 / num1)
                    yushu = (yushu * 10) % num1

                }
            }

        val bool = listOf(denominator, numerator).let { it.all { it >= 0 } || it.all { it <= 0 } }

        return if (bool) result else "-$result"
    }
}

