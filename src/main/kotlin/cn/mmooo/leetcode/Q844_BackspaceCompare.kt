package cn.mmooo.leetcode

fun main(args: Array<String>) {
    backspaceCompare(S = "#ab##", T = "#c#d#")
            .let { println(it) }


}

fun backspaceCompare(S: String, T: String): Boolean {

    fun toStr(s: String): String {
        var str = s
        val regex = Regex("[a-z]#")
        while (regex.containsMatchIn(str)) {
            str = str.replace(regex = regex, replacement = "")
        }
        return str
    }

    return toStr(S) == toStr(T)

}
