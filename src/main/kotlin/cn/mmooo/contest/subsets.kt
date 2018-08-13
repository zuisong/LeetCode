package cn.mmooo.contest

/**
给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。

说明：解集不能包含重复的子集。

示例:

输入: nums = [1,2,3]
输出:
[
[3],
[1],
[2],
[1,2,3],
[1,3],
[2,3],
[1,2],
[]
]
 */
fun subsets(nums: IntArray): List<List<Int>> {

    val result = arrayListOf<List<Int>>()
    fun dfs(index: Int, route: List<Int>) {
        if (index > nums.lastIndex) {
            result.add(route)
            return
        }
        dfs(index + 1, route.plus(nums[index]))
        dfs(index + 1, route)
    }

    dfs(0, emptyList())

    return result
}

fun main(args: Array<String>) {
    subsets(intArrayOf(1, 2, 3,4)).let(::println)
}