import kotlin.math.absoluteValue

/*
'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true
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
