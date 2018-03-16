package cn.mmooo.q151

import java.math.BigInteger
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {

    val num1 = "4565467646468465461354585527827825578272782872872343878"
    val num2 = "54534875454348527278278752527827278287453484354843548"
    val result = BigInteger(num1) * BigInteger(num2)
    println(result)
    repeat(10) {
        measureTimeMillis {
            val result1 = multiply(num1, num2)
            assert(result1 == result.toString())
        }.let {
            println("耗时 $it ms")
        }
    }
    println("$num1 * $num2 = $result")

}


/**
 * 返回两个字符串的乘积
 *
Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2.

Note:

The length of both num1 and num2 is < 110.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */


fun multiply(num1: String, num2: String): String {

    /**
     * 对两个数组做加法
     * 例如:[1][2][3] + [8][9] = [2][1][2]
     */
    val plusTwoIntArray = { num1: IntArray, num2: IntArray ->
        num1.reverse()
        num2.reverse()
        var jinwei = 0
        val result = IntArray(222, { -1 })
        val maxIndex = Math.max(num1.indexOfLast { it > 0 }, num2.indexOfLast { it > 0 })
        (0..maxIndex).forEach {
            val sum = num1.getOrElse(it) { 0 } + num2.getOrElse(it) { 0 } + jinwei
            jinwei = sum.div(10)
            result[it] = sum.rem(10)
        }
        result[maxIndex + 1] = jinwei

        result.copyOfRange(0, result.indexOfLast { it > 0 } + 1)
                .reversed()
                .let {
                    if (it.isNotEmpty()) {
                        it.toIntArray()
                    } else {
                        IntArray(1, { 0 })
                    }
                }

    }

    /**
     * 一个数组乘以一个数字
     * 例如  [5][9] * 2 = [1][1][8]
     */
    val intArrayMutiInt = { arr: IntArray, n: Int ->
        var sum = IntArray(1)

        repeat(n) {
            sum = plusTwoIntArray(sum.copyOf(), arr.copyOf())
        }
        sum
    }

    return num1.reversed().mapIndexed { index, c ->
        intArrayMutiInt(num2.plus("0".repeat(index)).map(Character::getNumericValue).toIntArray(), c.toString().toInt())
    }.reduce(plusTwoIntArray).joinToString("")
}

