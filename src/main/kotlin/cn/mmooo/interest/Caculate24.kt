package cn.mmooo.interest

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.set
import kotlin.math.*


fun main() {
    val ints = intArrayOf(5, 10, 4, 4)
    caculate24All(*ints)
            .let {
                println("{${ints.joinToString()}} 算24的解法有")
                println(it)
            }
}

/**
 * 这个函数可以求 任意个数算24问题 ，由于时间复杂度过高, 建议不要超过6位, 五位及其以下最佳
 */
fun caculate24All(vararg args: Int): String {

    if (args.size >= 6) {
        println("==== 要算很久, 请耐心等待 ====")
    }

    val list = cacuN(args.map { Pair(it.toDouble(), "$it") })

    val result = list
            .filter { abs(it.first - 24) < 1e-8 }
            .map { it.second }
            .distinct()
    return if (result.isEmpty()) "无解" else result.joinToString("\n")
}

fun String.addPair(): String = if (toIntOrNull() != null) this else "($this)"

/**
 * 2个数四则运算后的结果情况
 */
fun cacu2(num1: Pair<Double, String>, num2: Pair<Double, String>) =
        ArrayList<Pair<Double, String>>().also {
            it.add(Pair(num1.first + num2.first, "${num1.second.addPair()} + ${num2.second.addPair()}"))
            it.add(Pair(num1.first - num2.first, "${num1.second.addPair()} - ${num2.second.addPair()}"))
            it.add(Pair(num2.first - num1.first, "${num2.second.addPair()} - ${num1.second.addPair()}"))
            it.add(Pair(num1.first * num2.first, "${num1.second.addPair()} * ${num2.second.addPair()}"))

            if (num2.first != 0.0) {
                it.add(Pair(num1.first / num2.first, "${num1.second.addPair()} / ${num2.second.addPair()}"))
            }

            if (num1.first != 0.0) {
                it.add(Pair(num2.first / num1.first, "${num2.second.addPair()} / ${num1.second.addPair()}"))
            }
        }


/**
 * 任意个数四则运算后的结果情况
 *
 * 用到了递归和保存中间结果的算法思想
 */
val cacheHashMap = HashMap<String, List<Pair<Double, String>>>()

fun cacuN(parameters: List<Pair<Double, String>>): List<Pair<Double, String>> {
    if (parameters.size <= 1)
        return parameters

    val key = parameters.sortedBy { it.first }.joinToString("") { it.second }
    if (cacheHashMap.containsKey(key)) {
        return cacheHashMap[key]!!
    }

    val list = ArrayList<Pair<Double, String>>()
    parameters.forEach { e ->
        cacuN(parameters.minus(element = e)).forEach {
            list.addAll(cacu2(it, e))
        }
    }
    cacheHashMap[key] = list
    return list
}



