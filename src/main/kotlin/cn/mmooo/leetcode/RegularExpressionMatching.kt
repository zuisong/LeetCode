package cn.mmooo.leetcode

/*
'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool cn.mmooo.leetcode.isMatch(const char *s, const char *p)

Some examples:
cn.mmooo.leetcode.isMatch("aa","a") → false
cn.mmooo.leetcode.isMatch("aa","aa") → true
cn.mmooo.leetcode.isMatch("aaa","aa") → false
cn.mmooo.leetcode.isMatch("aa", "a*") → true
cn.mmooo.leetcode.isMatch("aa", ".*") → true
cn.mmooo.leetcode.isMatch("ab", ".*") → true
cn.mmooo.leetcode.isMatch("aab", "c*a*b") → true
*/
fun isMatch(s: String, p: String): Boolean {

    return true
}




fun main(args: Array<String>) {
    isMatch("aa", "a")
    isMatch("aa", "aa")
    isMatch("aaa", "aa")
    isMatch("aa", "a*")
    isMatch("aa", ".*")
    isMatch("ab", ".*")
    isMatch("aab", "c*a*b")

}


fun longestCommonPrefix(strs: Array<String>): String {
    val s = strs.firstOrNull().orEmpty()
    return s.filterIndexed { i, _ -> strs.all { it.length > i && it.slice(0..i) == s.slice(0..i) } }
}
