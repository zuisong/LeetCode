package cn.mmooo.q151

fun main(args: Array<String>) {

    val start = System.currentTimeMillis()
    val result = multiply(
            "14",
            "14"
    )

    println(result)
    println(System.currentTimeMillis() - start)
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

    result
            .copyOfRange(0, result.indexOfLast { it > 0 } + 1)
            .reversed()
            .let {
                if (it.isNotEmpty()) {
                    it.toIntArray()
                } else {
                    IntArray(1, { 0 })
                }
            }

}

fun multiply(num1: String, num2: String): String {

    fun String.toIntArray() =
            this.map(Character::getNumericValue).toIntArray()


    val intArrayMutiInt = { arr: IntArray, n: Int ->
        var sum = IntArray(1)

        repeat(n) {
            sum = plusTwoIntArray(sum.copyOf(), arr.copyOf())
        }
        sum
    }


    return num1.reversed().mapIndexed { index, c ->
        intArrayMutiInt(num2.plus("0".repeat(index)).toIntArray(), c.toString().toInt())
    }.reduce(plusTwoIntArray).joinToString("")
}

