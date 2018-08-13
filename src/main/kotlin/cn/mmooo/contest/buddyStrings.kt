package cn.mmooo.contest


fun main(args: Array<String>) {
    buddyStrings("aa", "aa").let(::println)
    buddyStrings("ab", "ab").let(::println)
    buddyStrings("abc", "bac").let(::println)
}

/**
 * 给定两个由小写字母构成的字符串 A 和 B ，只要我们可以通过交换 A 中的两个字母得到与 B 相等的结果，就返回 true ；否则返回 false 。
 *
示例 1：

输入： A = "ab", B = "ba"
输出： true

示例 2：

输入： A = "ab", B = "ab"
输出： false

示例 3：

输入： A = "aa", B = "aa"
输出： true
 */
fun buddyStrings(A: String, B: String): Boolean {

    if (A.length != B.length) return false

    if (A == B) {
        return A.toCharArray().distinct().size < A.length
    }

    var i = 0
    var j = A.length - 1
    var hasChanged = false
    while (j >= i) {
        if (A[i] == B[i]) {
            i++
            continue
        }

        if (A[j] == B[j]) {
            j--
            continue
        }


        if (!hasChanged && A[i] == B[j] && A[j] == B[i]) {
            hasChanged = true
            i++
            j--
            continue
        } else {
            return false
        }


    }


    return true
}