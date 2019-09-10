package cn.mmooo.interest

fun main(args: Array<String>) {
    val queenNumber = 8
    val emptyGrid = "+ "
    val queenGrid = "Q "
    var result: List<List<String>> = listOf()


    println(Test2.a)


    val array = IntArray(queenNumber)

    val saveResult: (IntArray) -> Unit = { res ->
        val resArr: List<String> = List(queenNumber) {
            StringBuffer()
                    .append(emptyGrid.repeat(res[it]))
                    .append(queenGrid)
                    .append(emptyGrid.repeat(queenNumber - res[it] - 1))
                    .toString()
        }
        result = result.plus(element = resArr)
    }

    var resultNumber = 0
    fun solve(arr: IntArray, row: Int) {
        (0 until queenNumber).filter { column ->
            (0 until row).none { Math.abs((arr[it] - column)) == (row - it) || arr[it] == column }
        }.forEach { column ->
            arr[row] = column
            solve(arr, row + 1)
            if (row == queenNumber - 1) {
                resultNumber++
                saveResult(arr)
            }
        }
    }

    solve(array, 0)

    println("一共有 $resultNumber 种解法\n")

    result.forEachIndexed { index, item ->
        println("第 ${index + 1} 种解法")
        item.forEach { println(it) }
        println()
        println()
    }
}
