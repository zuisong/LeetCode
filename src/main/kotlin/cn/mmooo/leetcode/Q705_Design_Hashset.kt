package cn.mmooo.leetcode

import cn.mmooo.adt.*
import cn.mmooo.adt.impl.*

/**
不使用任何内建的哈希表库设计一个哈希集合

具体地说，你的设计应该包含以下的功能

add(value)：向哈希集合中插入一个值。
contains(value) ：返回哈希集合中是否存在这个值。
remove(value)：将给定值从哈希集合中删除。如果哈希集合中没有这个值，什么也不做。

示例:

MyHashSet hashSet = new MyHashSet();
hashSet.add(1);
hashSet.add(2);
hashSet.contains(1);    // 返回 true
hashSet.contains(3);    // 返回 false (未找到)
hashSet.add(2);
hashSet.contains(2);    // 返回 true
hashSet.remove(2);
hashSet.contains(2);    // 返回  false (已经被删除)

注意：

所有的值都在 [1, 1000000]的范围内。
操作的总数目在[1, 10000]范围内。
不要使用内建的哈希集合库。
 */
class MyHashSet(
        private val loadFactor: Double = 0.75
) {

    /** Initialize your data structure here. */
    var table: Array<Any?>

    init {
        table = Array(8) { null }
    }


    private var currentSize = 0

    private fun getIndex(key: Int): Int {
        return key and table.lastIndex
    }

    fun add(key: Int) {

        ensureTableSize()

        val i = getIndex(key)
        val grid = table[i]
        if (grid == null) {
            table[i] = key
        } else if (grid is BinaryTree<*>) {
            grid as BinaryTree<Int>
            grid.insert(key)
        } else {
            val tree: BinaryTree<Int> = SimpleBinaryTreeImpl()
            tree.insert(grid as Int)
            tree.insert(key)
            table[i] = tree
        }
        currentSize++
    }

    private fun ensureTableSize(ensureSize: Int = 1) {
        if (table.size * loadFactor > ensureSize + currentSize)
            return
        val newTable = Array<Any?>(table.size * 2) { null }
        for ((idx, item) in table.withIndex()) {


        }

        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun remove(key: Int) {
        if (contains(key)) {
            val i = getIndex(key)
            val grid = table[i]
            if (grid is BinaryTree<*>) {
                grid as BinaryTree<Int>
                grid.remove(key)
            } else {
                table[i] = null
            }
            currentSize--
        }
    }

    /** Returns true if this set did not already contain the specified element */
    fun contains(key: Int): Boolean {
        val i = getIndex(key)
        val grid = table[i]
        if (grid is BinaryTree<*>) {
            grid as BinaryTree<Int>
            return grid.contains(key)
        } else {
            return table[i] == key
        }
    }

}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * var obj = MyHashSet()
 * obj.add(key)
 * obj.remove(key)
 * var param_3 = obj.contains(key)
 */