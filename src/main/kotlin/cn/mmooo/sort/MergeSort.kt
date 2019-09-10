package cn.mmooo.sort


fun main(args: Array<String>) {
    val nums = intArrayOf(1, 1545, 5, 7, 8, 32, 8, 431, 432, 53, 445, 12, 45)
    val result = mergeSort(nums)
    println(result.toList())
}

fun mergeSort(arr: IntArray, s: Int = 0, e: Int = arr.lastIndex): IntArray {

    if (s == e) {
        return arr.copyOfRange(s, s + 1)
    }
    val midIndex = (s + e) / 2
    val left = mergeSort(arr, s, midIndex)
    val right = mergeSort(arr, midIndex + 1, e)
    return merge(left, right)
}

fun merge(left: IntArray, right: IntArray): IntArray {

    val leftSize = left.size
    val rightSize = right.size
    val result = IntArray(leftSize + rightSize)

    var i = 0
    var j = 0
    while (i < leftSize || j < rightSize) {
        val l = left.getOrElse(i, { Int.MAX_VALUE })
        val r = right.getOrElse(j, { Int.MAX_VALUE })
        if (l <= r) {
            result[i + j] = l; i++
        } else {
            result[i + j] = r; j++
        }
    }

    return result

}

