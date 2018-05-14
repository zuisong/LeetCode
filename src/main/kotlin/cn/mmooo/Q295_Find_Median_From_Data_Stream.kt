package cn.mmooo.q295

import cn.mmooo.adt.BinaryHeap
import cn.mmooo.adt.impl.SimpleBinaryHeapImpl

/**
中位数是排序后列表的中间值。如果列表的大小是偶数，则没有中间值，此时中位数是中
间两个数的平均值。

示例:

[2,3,4] , 中位数是 3

[2,3], 中位数是 (2 + 3) / 2 = 2.5

设计一个支持以下两种操作的数据结构：

void addNum(int num) - 从数据流中增加一个整数到数据结构中。
double findMedian() - 返回目前所有元素的中位数。
例如：

addNum(1)
addNum(2)
findMedian() -> 1.5
addNum(3)
findMedian() -> 2
 */
fun main(args: Array<String>) {
    val medianFinder = MedianFinder()
    medianFinder.findMedian().let(::println)
    medianFinder.addNum(5)
    medianFinder.addNum(4)
    medianFinder.addNum(8)
    medianFinder.findMedian().let(::println)
    medianFinder.addNum(9)
    medianFinder.findMedian().let(::println)

}

class MedianFinder {

    private val maxHeap = SimpleBinaryHeapImpl<Int>(BinaryHeap.HeapType.MAX_HEAP)
    private val minHeap = SimpleBinaryHeapImpl<Int>(BinaryHeap.HeapType.MIN_HEAP)

    /** initialize your data structure here. */
    fun addNum(num: Int) {
        when {
            maxHeap.getTopOrNull() ?: Int.MIN_VALUE < num -> minHeap.insert(num)
            else -> maxHeap.insert(num)
        }

        when {
            minHeap.size > maxHeap.size + 1 -> {
                val i = minHeap.removeTop()
                maxHeap.insert(i)
            }
            maxHeap.size > minHeap.size -> {
                val i = maxHeap.removeTop()
                minHeap.insert(i)
            }
        }


    }

    fun findMedian(): Double {
        return if (minHeap.size > maxHeap.size) {
            minHeap.getTop().toDouble()
        } else {
            (maxHeap.getTopOr(0) + minHeap.getTopOr(0)) / 2.0
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * var obj = MedianFinder()
 * obj.addNum(num)
 * var param_2 = obj.findMedian()
 */
