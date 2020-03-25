package cn.mmooo.adt.impl

import cn.mmooo.adt.BinaryTree
import cn.mmooo.adt.Queue

fun main() {
    val tree = SimpleBinaryTreeImpl<Int>()

    tree.insert(5)
    tree.insert(4)
    tree.insert(3)
    tree.insert(1)
    tree.insert(6)
    println(tree)

}

class SimpleBinaryTreeImpl<E : Comparable<E>> : BinaryTree<E> {

    override fun toString(): String {
        return "我是一棵二叉树"
    }

    private var currentSize = 0

    override val size: Int
        get() = currentSize

    override fun containsAll(elements: Collection<E>): Boolean =
            elements.parallelStream().anyMatch(this::contains)

    override fun iterator(): Iterator<E> = object : Iterator<E> {
        private val queue: Queue<BinaryNode<E>>

        init {
            queue = QueueAndStack()
            if (root != null) {
                queue.push(root!!)
            }
        }

        override fun hasNext(): Boolean = queue.isNotEmpty()

        override fun next(): E {
            val node = queue.poll()
            if (node.left != null) queue.push(node.left!!)
            if (node.right != null) queue.push(node.right!!)
            return node.ele
        }

    }

    private var root: BinaryNode<E>? = null

    override fun isEmpty(): Boolean {
        return root == null
    }

    init {
        makeEmpty()
    }

    override fun makeEmpty() {
        root = null
        currentSize = 0
    }

    private fun insert(e: E, node: BinaryNode<E>?): BinaryNode<E> {
        if (node == null) {
            return BinaryNode(e)
        }
        when {
            e < node.ele -> node.left = insert(e, node.left)
            e > node.ele -> node.right = insert(e, node.right)
            else -> {
                // 处理已经存在的情况
                TODO("处理已经存在的情况")
            }
        }
        return node
    }

    override fun insert(ele: E) {
        root = insert(ele, root)
        currentSize++
    }

    override fun findMax(): E {
        if (isEmpty()) {
            throw ArithmeticException()
        }
        return findMax(root!!).ele
    }

    private fun findMax(node: BinaryNode<E>): BinaryNode<E> {
        return if (node.right == null) {
            node
        } else {
            findMax(node.right!!)
        }
    }

    override fun findMin(): E {
        if (isEmpty()) {
            throw ArithmeticException()
        }
        return findMin(root!!).ele
    }

    private fun findMin(node: BinaryNode<E>): BinaryNode<E> {
        return if (node.left == null) {
            node
        } else {
            findMin(node.left!!)
        }
    }

    override fun remove(x: E) {
        root = remove(x, root)
        currentSize--
    }

    private fun remove(ele: E, node: BinaryNode<E>?): BinaryNode<E>? {
        if (node == null) {
            return node
        }
        when {
            ele < node.ele -> node.left = remove(ele, node.left)
            ele > node.ele -> node.right = remove(ele, node.right)
            else -> {
                if (node.left != null && node.right != null) {
                    node.ele = findMin(node.right!!).ele
                    node.right = remove(node.ele, node.right)
                } else {
                    return if (node.left != null) node.left else node.right
                }
            }
        }
        return node
    }


    private fun contains(ele: E, node: BinaryNode<E>?): Boolean {
        if (node == null) {
            return false
        }
        return when {
            ele > node.ele -> contains(ele, node.right)
            ele < node.ele -> contains(ele, node.left)
            else -> true
        }
    }

    override operator fun contains(ele: E): Boolean {
        return contains(ele, root)
    }

    private class BinaryNode<T>(var ele: T) {
        var left: BinaryNode<T>? = null // 左节点
        var right: BinaryNode<T>? = null // 右节点
    }
}
