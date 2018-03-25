package cn.mmooo.q380

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


fun main(args: Array<String>) {

}

/**
Design a data structure that supports all following operations in average O(1) time.

insert(val): Inserts an item val to the set if not already present.
remove(val): Removes an item val from the set if present.
getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
 */
class RandomizedSet() {

    /** Initialize your data structure here. */

    private val hashMap = HashMap<Int, Int>()
    private val list = ArrayList<Int>()

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    fun insert(`val`: Int): Boolean {
        val notContains = !hashMap.contains(`val`)
        if (notContains) {
            list.add(`val`)
            hashMap[`val`] = list.size - 1
        }
        return notContains
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    fun remove(`val`: Int): Boolean {
        val contains = hashMap.contains(`val`)
        if (contains) {
            val i1 = hashMap[`val`]!!
            hashMap[list.last()] = i1

            list[i1] = list.last()

            list.removeAt(list.lastIndex)
            hashMap.remove(`val`)
        }
        return contains
    }

    /** Get a random element from the set. */
    fun getRandom(): Int {
        val i = random.nextInt(list.size)
        return list[i]
    }


    private val random = Random()
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * var obj = RandomizedSet()
 * var param_1 = obj.insert(`val`)
 * var param_2 = obj.remove(`val`)
 * var param_3 = obj.getRandom()
 */