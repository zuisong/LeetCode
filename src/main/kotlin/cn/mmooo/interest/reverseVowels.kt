package cn.mmooo.interest

fun main(args: Array<String>) {
    reverseVowels("chenjian").let(::println)
}

fun reverseVowels(s: String): String {

    val array = s.toCharArray()

    var i = 0
    var j = array.lastIndex

    fun Char.isVowel(): Boolean = this in "aoeiuv"

    while (i < j) {
        while (i < j && !array[i].isVowel()) {
            i++
        }

        while (i < j && !array[j].isVowel()) {
            j--
        }
        val temp = array[i]
        array[i] = array[j]
        array[j] = temp

        i++
        j--

    }





    return String(array)
}