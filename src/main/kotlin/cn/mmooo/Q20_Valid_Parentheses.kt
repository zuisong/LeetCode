package cn.mmooo.q20

import java.util.*


fun main(args: Array<String>) {
    Solution().isValid("]")
            .let { println(it) }
}

/**
给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

括号必须以正确的顺序关闭，"()" 和 "()[]{}" 是有效的但是 "(]" 和 "([)]" 不是。
 */
class Solution {

    private val map = hashMapOf('(' to ')', '{' to '}', '[' to ']')
    fun isValid(s: String): Boolean {
        val stack: Stack<Char> = Stack()
        s.forEach { c ->
            if (c in map) {
                stack.push(c)
            } else {
                if (stack.isEmpty() || stack.pop().let { map[it] } != c) {
                    return false
                }
            }
        }
        return stack.isEmpty()
    }
}

