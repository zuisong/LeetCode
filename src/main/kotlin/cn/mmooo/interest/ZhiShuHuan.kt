package zhishuhuan

import kotlin.system.*


fun main(args: Array<String>) {
    measureTimeMillis {
        zhishuhuan(16)
                .let { println(it) }
    }.let { println("耗时 $it ms") }

}

/**
 *  质数环: 1 到 [numberSize]  按某个顺序这些数排成一个环，要求相邻的两个数相加为质数
 *  @return 一共有多少种排列方法
 */
fun zhishuhuan(numberSize: Int): Int {
    val zhishus =
            (4 until numberSize * 2)
                    .filter {
                        (2..Math.sqrt(1.0 * it).toInt())
                                .none { i -> it % i == 0 }
                    }.plus(arrayOf(2, 3)).toHashSet()

    fun Int.isZhiShu(): Boolean =
            zhishus.contains(this)

    var count = 0

    fun solve(index: Int, arr: IntArray) {
        (1..numberSize).filter { !arr.copyOfRange(0, index).contains(it) }
                .filter {
                    index == 0 || (it + arr[index - 1]).isZhiShu()
                }.forEach {
                    arr[index] = it
                    if (index < numberSize - 1) {
                        solve(index + 1, arr)
                    } else if (index == numberSize - 1 && (it + arr[0]).isZhiShu()) {
                        count++
//                        arr.toList()
                        return
                    }
                }
    }

    val array = IntArray(numberSize)
    array[0] = 1
    solve(1, array)
    return count
}