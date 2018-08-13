package cn.mmooo.contest

fun main(args: Array<String>) {
    backspaceCompare(S = "#ab##", T = "#c#d#")
            .let { println(it) }


}

fun backspaceCompare(S: String, T: String): Boolean {


    return toStr(S) == toStr(T)

}

fun toStr(s: String): String {
    var str = s
    val regex = Regex("[a-z]#")
    while (regex.containsMatchIn(str)) {
        str = str.replace(regex = regex, replacement = "")
    }
    return str
}
