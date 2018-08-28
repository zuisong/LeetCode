package cn.mmooo.leetcode

import java.util.*

/**
There are n cities connected by m flights. Each fight starts
from city u and arrives at v with a price w.

Now given all the cities and fights, together with starting
city src and the destination dst, your task is to find the
cheapest price from src to dst with up to k stops. If there
is no such route, output -1.
 */
fun main(args: Array<String>) {

}

fun findCheapestPrice(n: Int, flights: Array<IntArray>, src: Int, dst: Int, K: Int): Int {
    //Triple<Int, Int, Int> 里存的是   当前点位置，从src到当前点要多远，从src到当前点走了几步
    val q = LinkedList<Triple<Int, Int, Int>>()
    q.add(Triple(src, 0, 0))
    var minResult = Int.MAX_VALUE
    val map = hashMapOf<Int, Triple<Int, Int, Int>>()
    while (q.isNotEmpty()) {
        val poll = q.poll()
        if (poll.first == dst) {
            minResult = Math.min(minResult, poll.second)
        } else if (poll.third <= K) {
            // 迭代当前位置可以到达的点，删选出总距离小于已记录的点，加入队列中
            flights.filter { it.first() == poll.first }
                    .forEach {
                        val triple = map[it[1]]
                        if (triple == null || triple.second > it[2] + poll.second) {
                            val element = Triple(it[1], it[2] + poll.second, poll.third + 1)
                            map[it[1]] = element
                            q.add(element)
                        }
                    }

        }
    }

    return if (minResult == Int.MAX_VALUE) -1 else minResult
}
