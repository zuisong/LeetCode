package cn.mmooo.leetcode

fun main(args: Array<String>) {

    println(removeKdigits("1432219", 3))

}

/**
 * 给定的数字 [num] 中移除 [k] 位 , 返回最小数字
 */
fun removeKdigits(num: String, k: Int): String {

    /**
     * 删除一位，返回最小值
     * 策略:从高位到低位，删除第一个数值下降的
     *     如   15471 删除 5     205 删除 2
     *     一直是上升的则删除最后一位 123 删除 3
     */
    val remove1Digits = { s: String ->
        val i = (0 until s.length - 1).find { s[it] > s[it + 1] } ?: s.length-1
        s.filterIndexed { index, _ -> index != i }.dropWhile { it == '0' }
                .let { if (it.isEmpty()) "0" else it }
    }

    var result = num

    repeat(k) {
        result = remove1Digits(result)
    }
    return result
}


