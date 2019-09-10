package cn.mmooo.contest

/**
 * @links https://leetcode-cn.com/explore/interview/card/top-interview-questions-medium/49/backtracking/91/
 */
fun letterCombinations(digits: String): List<String> {

    val digitsToChars =
            mapOf(
                    '2' to "abc",
                    '3' to "def",
                    '4' to "ghi",
                    '5' to "jkl",
                    '6' to "mno",
                    '7' to "pqrs",
                    '8' to "tuv",
                    '9' to "wxyz"
            ).mapValues { it.value.toCharArray() }


    var result = MutableList(1 shr digits.length) { "" }

    fun dfs(index: Int, str: StringBuilder) {
        if (index >= digits.length) {
            result.add(str.toString())
            return
        }

        for (c in digitsToChars[digits[index]] ?: error("输入有误")) {
            dfs(index + 1, str.append(c))
            str.deleteCharAt(str.lastIndex)
        }
    }



    dfs(0, StringBuilder(digits.length))

    return result
}

fun main(args: Array<String>) {
    letterCombinations("24556568568")
            .forEach(::println)
}