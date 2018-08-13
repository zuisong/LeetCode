package cn.mmooo.q139


fun main(args: Array<String>) {
    wordBreak("ccaccc", listOf("cc", "ac")).let(::println)
    wordBreak("ccbb", listOf("bc", "cb")).let(::println)
    wordBreak("applepenapple", listOf("apple", "pen")).let(::println)
    wordBreak("catsandog", listOf("cats", "dog", "sand", "and", "cat")).let(::println)
}

/**
 *
 * 动态规划算法
 * 第一步： 找递推关系   ——>   s(n)=true <= any( s(m)==true   and str(m..n) in @param wordDict )
 * 第二步： 缓存递推关系中需要的结果 cache s(m)
 * 第三步： 求解
 */
fun wordBreak(s: String, wordDict: List<String>): Boolean {
    if (s.isEmpty())
        return true
    val dict = wordDict.toMutableSet()
    val res = BooleanArray(s.length + 1)
    res[0] = true

    for (i in 0..s.lastIndex) {
        val tempStr = StringBuilder(s.substring(0..i))
        for (j in 0..i) {
            if (tempStr.toString() in dict) {
                res[i + 1] = res[j]
                break
            }
        }
        tempStr.deleteCharAt(0)
    }

    return res.last()
}


