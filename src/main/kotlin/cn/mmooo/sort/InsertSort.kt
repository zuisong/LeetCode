package cn.mmooo

fun main(args: Array<String>) {

    val nums = intArrayOf(5, 4, 8, 6, 4, 52, 41, 12, 23, 14, 11)
    println(nums.toList())
    insertSort(nums)
    println(nums.toList())

}

fun insertSort(nums: IntArray) {
    (1 until nums.size).forEach { i ->
        var j = i
        val jValue = nums[j]
        while (j > 0 && jValue < nums[j - 1]) {
            nums[j] = nums[j - 1]
            j--
        }
        nums[j] = jValue
    }
}