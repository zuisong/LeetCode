package cn.mmooo.interest

import java.util.*

/**
 * 计算表达式的值，支持括号 + - / * ,按优先级运算  1+2*3 = 7 而不是9
 */
fun main(args: Array<String>) {
    val express = "1 + 2 * ( 3 + 1 )".split(" ")
    caculate(express).let {
        println("${express.joinToString(separator = "")}=$it")
    }
}


fun infixToSuffix(source: List<String>): Queue<String> {
    val map = mapOf("+" to 1, "-" to 1, "*" to 2, "/" to 2, "(" to 0)
    val stack = Stack<String>()
    val queue: Queue<String> = LinkedList<String>()
    source.forEach {
        if (it in map.keys) {
            val i = map[it]
            while (it != "(" && stack.isNotEmpty() && stack.peek().let { k -> map[k]!! } >= i!!) {
                queue.add(stack.pop())
            }
            stack.push(it)
        } else {
            if (it == ")") {
                while (true) {
                    val s = stack.pop()
                    if (s != "(")
                        queue.add(s)
                    else
                        break
                }
            } else {
                queue.add(it)
            }
        }
    }
    while (stack.isNotEmpty()) {
        queue.add(stack.pop())
    }
    return queue
}

fun caculate(express: List<String>): Double {


    val queue = infixToSuffix(express)


    val stack = Stack<String>()
    while (queue.isNotEmpty()) {
        val c = queue.poll()
        if (c in arrayOf("+", "-", "*", "/")) {
            val i1 = stack.pop().toDouble()
            val i2 = stack.pop().toDouble()
            val res = when (c) {
                "+" -> i2 + i1
                "-" -> i2 - i1
                "*" -> i2 * i1
                "/" -> i2 / i1
                else -> 0.0
            }
            stack.push(res.toString())
        } else {
            stack.push(c)
        }
    }
    return stack.pop().toString().toDouble()
}

