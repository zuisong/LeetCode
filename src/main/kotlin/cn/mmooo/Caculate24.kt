package cn.mmooo

import java.util.HashMap

fun main(args: Array<String>) {
    caculate24All(3, 4, 5, 6, 7, 22)
            .let {
                println("3, 4, 5, 6, 7 算24的解法有")
                println(it)
            }
    println("==================")
    caculate24With4Num(3, 4, 5, 6)
            .let {
                println("3, 4, 5, 6 算24的解法有")
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

    val result = list.filter { it.first == 24.toDouble() }.map { it.second }.distinct()
    return if (result.isEmpty()) "无解" else result.joinToString("\n")
}

/**
 * 这个函数只能求解 四位数算24问题
 */
fun caculate24With4Num(num1: Int, num2: Int, num3: Int, num4: Int): String {
    val list = cacu4(
            Pair(num1.toDouble(), "$num1"),
            Pair(num2.toDouble(), "$num2"),
            Pair(num3.toDouble(), "$num3"),
            Pair(num4.toDouble(), "$num4")
    )

    val result = list.filter { it.first == 24.toDouble() }.map { it.second }.distinct()
    return if (result.isEmpty()) "无解" else result.joinToString("\n")
}

fun String.doA() = this.toIntOrNull() ?: "($this)"

/**
 * 2个数四则运算后的结果情况
 */
fun cacu2(num1: Pair<Double, String>, num2: Pair<Double, String>): List<Pair<Double, String>> {
    val list = ArrayList<Pair<Double, String>>()
    list.add(Pair(num1.first + num2.first, "${num1.second.doA()} + ${num2.second.doA()}"))
    list.add(Pair(num1.first - num2.first, "${num1.second.doA()} - ${num2.second.doA()}"))
    list.add(Pair(num2.first - num1.first, "${num2.second.doA()} - ${num1.second.doA()}"))
    list.add(Pair(num1.first * num2.first, "${num1.second.doA()} * ${num2.second.doA()}"))
    list.add(Pair(num1.first / num2.first, "${num1.second.doA()} / ${num2.second.doA()}"))
    list.add(Pair(num2.first / num1.first, "${num2.second.doA()} / ${num1.second.doA()}"))
    return list
}

/**
 * 3个数四则运算后的结果情况
 */
fun cacu3(num1: Pair<Double, String>, num2: Pair<Double, String>, num3: Pair<Double, String>): List<Pair<Double, String>> {
    val list = ArrayList<Pair<Double, String>>()
    cacu2(num1, num2).forEach {
        list.addAll(cacu2(num3, it))
    }
    cacu2(num3, num2).forEach {
        list.addAll(cacu2(num1, it))
    }
    cacu2(num1, num3).forEach {
        list.addAll(cacu2(num2, it))
    }
    return list
}
/**
 * 3个数四则运算后的结果情况
 */
fun cacu4(num1: Pair<Double, String>, num2: Pair<Double, String>, num3: Pair<Double, String>, num4: Pair<Double, String>): List<Pair<Double, String>> {
    val list = ArrayList<Pair<Double, String>>()
    cacu3(num1, num2, num3).forEach {
        list.addAll(cacu2(num4, it))
    }

    cacu3(num1, num2, num4).forEach {
        list.addAll(cacu2(num3, it))
    }

    cacu3(num1, num3, num4).forEach {
        list.addAll(cacu2(num2, it))
    }

    cacu3(num2, num3, num4).forEach {
        list.addAll(cacu2(num1, it))
    }

    return list
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



