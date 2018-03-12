package cn.mmooo.q35


private inline val Int.left get() = 2 * this + 1
private inline val Int.right get() = 2 * this + 2

private class Solution {
    fun searchInsert(nums: IntArray, target: Int): Int {
        var it = 0
        while (true) {
            val compareTo = nums[it].compareTo(target)
            it = when {
                compareTo == 0 -> return it
                compareTo < 0 ->
                    if (it.right >= nums.size) {
                        return it + 1
                    } else {
                        it.right
                    }
                else ->
                    if (it.left >= nums.size) {
                        return it - 1
                    } else {
                        it.left
                    }
            }
        }

    }
}

/**
Given a sorted array and a target value, return the index if the target is found.
If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.
 */
fun main(args: Array<String>) {
    Solution().searchInsert(intArrayOf(1, 3, 5, 6), 4)
            .let {
                println(it)
            }

}