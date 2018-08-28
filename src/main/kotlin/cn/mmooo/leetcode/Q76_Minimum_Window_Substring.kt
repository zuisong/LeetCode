package cn.mmooo.leetcode

/**
给定一个字符串 S 和一个字符串 T，请在 S 中找出包含 T 所有字母的最小子串。

示例：

输入: S = "ADOBECODEBANC", T = "ABC"
输出: "BANC"
说明：

如果 S 中不存这样的子串，则返回空字符串 ""。
如果 S 中存在这样的子串，我们保证它是唯一的答案。
 */
fun minWindow(s: String, t: String): String {


    if(t.isEmpty()) return ""

    val hashSet = t.toCharArray().toHashSet()
    val valueIndexs =
            s.toCharArray()
                    .withIndex()
                    .filter { hashSet.contains(it.value) }
                    .groupBy({ it.value }, { it.index })
                    .values

    if (valueIndexs.size != t.length) return ""

    var i = 0
    var j = 0
    var result = s
    while (j < s.length) {
        val b = valueIndexs.all { idxs -> idxs.any { it in i..j } }
        if (b) {
            if (result.length > j - i + 1) {
                result = s.substring(i..j)
            }
            i++
        } else j++
    }

    return result

}

fun main(args: Array<String>) {
    minWindow(s = "ADOBECODEBANE", t = "ABC").let(::println)
}