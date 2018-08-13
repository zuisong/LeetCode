package cn.mmooo.contest


/**
给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句子。

说明：

分隔时可以重复使用字典中的单词。
你可以假设字典中没有重复的单词。
示例 1：

输入:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
输出:
[
"cats and dog",
"cat sand dog"
]
示例 2：

输入:
s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
输出:
[
"pine apple pen apple",
"pineapple pen apple",
"pine applepen apple"
]
解释: 注意你可以重复使用字典中的单词。
示例 3：

输入:
s = "catsandog"
wordDict = ["cats", "dog", "sand", "and", "cat"]
输出:
[]
 */
fun wordBreak(s: String, wordDict: List<String>): List<String> {


    val dict = wordDict.toHashSet()
    val result = arrayListOf<String>()

    fun dfs(idx: Int, res: String) {

        if (idx == s.length) {
            result.add(res.trim())
        }

        for (i in idx until s.length) {
            val substring = s.substring(idx..i)
            val b = substring in dict
            if (b) {
                dfs(i + 1, "$res $substring")
            }
        }
    }
    dfs(0, "")
    return result
}

fun main(args: Array<String>) {
    wordBreak("catsanddog", listOf("cat", "cats", "and", "sand", "dog")).let(::println)
    wordBreak("pineapplepenapple", listOf("apple", "pen", "applepen", "pine", "pineapple")).let(::println)
}