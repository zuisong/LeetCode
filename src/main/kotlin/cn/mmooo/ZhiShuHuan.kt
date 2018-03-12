const val numberSize = 8

fun main(args: Array<String>) {


    val zhishuList =
            (2 until numberSize * 2)
                    .filter {
                        it <= 3 || (2..Math.sqrt(1.0 * it).toInt())
                                .none { i -> it % i == 0 }
                    }
    println(zhishuList)
    println()

    fun Int.isZhiShu(): Boolean =
            zhishuList.contains(this)

    fun solve(index: Int, arr: IntArray) {
        (1..numberSize)
                .filter { !arr.copyOfRange(0, index).contains(it) }
                .filter {
                    index == 0 || (it + arr[index - 1]).isZhiShu()
                }.forEach {
                    arr[index] = it
                    if (index < numberSize - 1) {
                        solve(index + 1, arr)
                    }
                    if (index == numberSize - 1 && (it + arr[0]).isZhiShu()) {
                        println(arr.toList())
//                        return
                    }
                }
    }

    val array = IntArray(numberSize)
    array[0] = 1
    System.currentTimeMillis()
    solve(1, array)
}