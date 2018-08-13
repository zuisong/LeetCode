package cn.mmooo

/**
Given an array and a value, remove all instances of that value in-place and return the new length.

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 */

fun main(args: Array<String>) {
    val arr = intArrayOf(1)
    val count = removeElement(arr, 1)
    println(count)
    println(arr.toList())
}

fun removeElement(nums: IntArray, `val`: Int): Int {

    var count = 0
    (0 until nums.size).forEach { i ->
        var j = i
        val tempValue = nums[i]

        if (tempValue != `val`) {
            count++
        }
        while (j > 0 && nums[j - 1] == `val`) {
            nums[j] = nums[j - 1]
            j--
        }
        nums[j] = tempValue
    }
    return count
}
