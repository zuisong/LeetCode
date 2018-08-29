package cn.mmooo.leetcode

import java.util.*

/**
实现 FreqStack，模拟类似栈的数据结构的操作的一个类。

FreqStack 有两个函数：

push(int x)，将整数 x 推入栈中。
pop()，它移除并返回栈中出现最频繁的元素。
如果最频繁的元素不只一个，则移除并返回最接近栈顶的元素。


示例：

输入：
["FreqStack","push","push","push","push","push","push","pop","pop","pop","pop"],
[[],[5],[7],[5],[7],[4],[5],[],[],[],[]]
输出：[null,null,null,null,null,null,null,5,7,5,4]
解释：
执行六次 .push 操作后，栈自底向上为 [5,7,5,7,4,5]。然后：

pop() -> 返回 5，因为 5 是出现频率最高的。
栈变成 [5,7,5,7,4]。

pop() -> 返回 7，因为 5 和 7 都是频率最高的，但 7 最接近栈顶。
栈变成 [5,7,5,4]。

pop() -> 返回 5 。
栈变成 [5,7,4]。

pop() -> 返回 4 。
栈变成 [5,7]。
 */
class FreqStack {

    private val stack = Stack<Int>()
    private val map = hashMapOf<Int, Int>()
    fun push(x: Int) {
        stack.push(x)
        map.compute(x) { _, v -> if (v != null) v + 1 else 1 }
    }

    fun pop(): Int {
        if (stack.isEmpty())
            throw IllegalAccessException("empty stack can not pop")

        val tempStack = Stack<Int>()
        val maxCountItem = map
                .asSequence()
                .groupBy({ it.value }, { it.key })
                .maxBy { it.key }!!.value
        var tempValue: Int
        while (true) {
            tempValue = stack.pop()
            if (tempValue !in maxCountItem) {
                tempStack.push(tempValue)
            } else {
                while (tempStack.isNotEmpty()) {
                    stack.push(tempStack.pop())
                }
                break
            }
        }
        map.compute(tempValue) { _, v -> v!! - 1 }
        map.remove(tempValue, 0)
        return tempValue
    }

}


fun main(args: Array<String>) {
    val stack = FreqStack()
    with(stack) {
        push(5)
        push(7)
        push(5)
        push(7)
        push(4)
        push(5)
        pop().let(::println)
        pop().let(::println)
        pop().let(::println)
        pop().let(::println)
        pop().let(::println)
        pop().let(::println)
    }
}

/**
 * Your FreqStack object will be instantiated and called as such:
 * var obj = FreqStack()
 * obj.push(x)
 * var param_2 = obj.pop()
 */