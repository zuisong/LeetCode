package cn.mmooo.leetcode

/**
给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。

示例:

输入: "25525511135"
输出: ["255.255.11.135", "255.255.111.35"]
 */
fun restoreIpAddresses(s: String): List<String> {

    val ipRange = 0..255
    val result = arrayListOf<String>()

    fun dfs(deep: Int, idx: Int, route: String) {
        if (deep > 4) {
            if (idx == s.length) {
                result.add(route.substring(1))
            }
            return
        }

        for (it in 1..3) {
            if (idx + it > s.length) continue
            val s1 = s.substring(idx, idx + it)
            if (it != 1 && s1.startsWith("0")) continue
            if (it == 3 && s1.toInt() !in ipRange) continue
            dfs(deep + 1, idx + it, "$route.$s1")
        }
    }


    dfs(1, 0, "")

    return result
}

fun main(args: Array<String>) {
    restoreIpAddresses("25525511135").let(::println)
}