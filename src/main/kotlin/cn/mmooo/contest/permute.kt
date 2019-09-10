package cn.mmooo.contest

/**
给定一个没有重复数字的序列，返回其所有可能的全排列。

示例:

输入: [1,2,3]
输出:
[
[1,2,3],
[1,3,2],
[2,1,3],
[2,3,1],
[3,1,2],
[3,2,1]
]
 */

fun permute(nums: IntArray): List<List<Int>> {

    val result = arrayListOf<List<Int>>()

    fun dfs(list: List<Int>, route: List<Int>) {

        if (list.isEmpty()) {
            result.add(route)
            return
        }

        list.forEach {
            dfs(list.minus(it), route.plus(it))
        }
    }
    dfs(nums.toList(), listOf())
    return result
}

fun main(args: Array<String>) {
    permute(intArrayOf(1, 2, 3, 4)).let(::println)
}