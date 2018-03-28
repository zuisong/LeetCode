package cn.mmooo.q303

fun main(args: Array<String>) {

}

class NumArray(val nums: IntArray) {

    fun sumRange(i: Int, j: Int): Int {
        var sum = 0

        (i..j).forEach {
            sum += nums[it]
        }

        return sum
    }

}