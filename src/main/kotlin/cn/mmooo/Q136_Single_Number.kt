package cn.mmooo.q136


fun main(args: Array<String>) {
    singleNumber(intArrayOf(1, 1, 100, 3, 3)).let(::println)

}

/**
给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。

说明：

你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 */
fun singleNumber(nums: IntArray): Int {
    (1..nums.lastIndex).forEach {
        nums[0] = nums[0] xor nums[it]
    }
    return nums.first()
}
