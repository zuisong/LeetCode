package cn.mmooo.q746

fun main(args: Array<String>) {
    intArrayOf(0, 0, 0, 1).let {
        Solution().minCostClimbingStairs(it)
    }
}

/**
On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).

Once you pay the cost, you can either climb one or two steps. You need to find minimum cost to reach the top of the floor, and you can either start from the step with index 0, or the step with index 1.

Example 1:
Input: cost = [10, 15, 20]
Output: 15
Explanation: Cheapest is start on cost[1], pay that cost and go to the top.
Example 2:
Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
Output: 6
Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[3].
 */
private class Solution {
    fun minCostClimbingStairs(cost: IntArray): Int {
        val minCost = IntArray(cost.size)
        minCost[0] = cost[0]
        minCost[1] = cost[1]

        (2..cost.lastIndex).forEach {
            minCost[it] = Math.min(minCost[it - 1], minCost[it - 2]) + cost[it]
        }
        println(minCost.toList())
        return Math.min(minCost.last(), minCost[minCost.lastIndex - 1])
    }
}