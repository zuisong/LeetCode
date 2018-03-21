package cn.mmooo.q3

import kotlin.system.measureTimeMillis

/**
Given a string, find the length of the longest substring without repeating characters.

Examples:

Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
fun main(args: Array<String>) {
    measureTimeMillis {

        Solution().lengthOfLongestSubstring(

                """
                tmmzuxt
                 """.trimIndent()
        )
                .let { println(it) }
    }.let { println(it) }
}


class Solution {
    fun lengthOfLongestSubstring(str: String): Int {


        // 暴力解法，通不过
        /*
        var result = 0
        (0..str.lastIndex).forEach { s ->
            (s..str.lastIndex).forEach { e ->
                val s1 = str.substring(s..e)
                if (s1.length > result && s1.length == s1.toCharArray().toSet().size) {
                    result = s1.length
                }
            }
        }
        */

        var lastUniqIndex = 0
        var maxUniqSubStringSize = 0

        val map = HashMap<Char, Int>()

        str.forEachIndexed { index, c ->
            if (c !in map.filterValues { it >= lastUniqIndex }) {
                map.put(c, index)
                if (index - lastUniqIndex + 1 > maxUniqSubStringSize) {
                    maxUniqSubStringSize = index - lastUniqIndex + 1
                }
            } else {
                lastUniqIndex = map[c]!! + 1
                map[c] = index
            }
        }

        return maxUniqSubStringSize
    }
}