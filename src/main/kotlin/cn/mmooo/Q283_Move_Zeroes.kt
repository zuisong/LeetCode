package cn.mmooo.q283

/**
Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].

Note:
You must do this in-place without making a copy of the array.
Minimize the total number of operations.
 */
fun main(args: Array<String>) {

    val arr = intArrayOf(0, 1, 0, 3, 12)
    moveZeroes(arr)
    print(arr.toList())

}

private fun IntArray.swap(i: Int, j: Int) {
    val tempValue = this[j]
    this[j] = this[i]
    this[i] = tempValue
}

fun moveZeroes(nums: IntArray): Unit {
    (1 until nums.size).forEach { i ->
        var j = i
        while (j > 0 && nums[j] >= 0 && nums[j - 1] == 0) {
            nums.swap(j, j - 1)
            j--
        }
    }
}
