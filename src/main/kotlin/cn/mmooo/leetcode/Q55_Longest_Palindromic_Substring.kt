package cn.mmooo.leetcode

fun main(args: Array<String>) {
    longestPalindrome("bb")
            .let { println(it) }
}

/**
Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example:

Input: "babad"

Output: "bab"

Note: "aba" is also a valid answer.
 */
/**
 * 最长回文字子串算法
 * 分奇数和偶数两种情况做，奇数个字符的肯定关于中间字符对称，选一个向两边扩充，记录迭代最大的情况
 * 偶数的就没有对称字符，可以选定一个字符前面的那一个间隔作为对称轴，向两边阔充，记录迭代最大的情况
 */
fun longestPalindrome(s: String): String {


    var maxX = 1


    var position = Pair(0, 0)

    repeat(s.lastIndex) {
        // 奇数的回文串，肯定以某个字符为对称轴
        var i = 1
        while (it - i >= 0 && it + i < s.length && s[it - i] == s[it + i]) {
            i++
        }
        i--
        if (2 * i + 1 > maxX) {
            maxX = 2 * i + 1
            position = Pair(it - i, it + i)
        }


        // 偶数的回文串
        i = 0
        while (it - i >= 0 && it + i + 1 < s.length && s[it - i] == s[it + i + 1]) {
            i++
        }
        if (2 * i > maxX) {
            maxX = 2 * i
            position = Pair(it - i + 1, it + i)
        }

    }


    return s.substring(position.first, position.second + 1)
}
