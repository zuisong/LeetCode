package cn.mmooo.adt.impl

import cn.mmooo.adt.BinaryTree


fun main(args: Array<String>) {
    val tree = SimpleBinaryTreeImpl<Int>()

    tree.insert(5)
    tree.insert(4)
    println(tree)

}

open class RedBlackBinaryTree<E : Comparable<E>> : BinaryTree<E> {
    override val size: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun containsAll(elements: Collection<E>): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun iterator(): Iterator<E> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private var root: BinaryNode<E>? = null

    override fun isEmpty(): Boolean {
        return root == null
    }

    init {
        root = null
    }

    override fun makeEmpty() {
        root = null
    }

    private fun insert(node: BinaryNode<E>) {
        var y: BinaryNode<E>? = null
        var x: BinaryNode<E>? = this.root

        // 1. 将红黑树当作一颗二叉查找树，将节点添加到二叉查找树中。
        while (x != null) {
            y = x
            x = when {
                node.ele > x.ele -> x.right
                node.ele < x.ele -> x.left
                else -> return
            }
        }


        node.parent = y
        if (y != null) {
            if (node.ele < y.ele) y.left = node else y.right = node
        } else {
            this.root = node
        }

        // 2. 设置节点的颜色为红色
        node.color = RED
        node.left = null
        node.right = null


        // 3. 将它重新修正为一颗二叉查找树
        insertFixUp(node)
    }

    private fun insertFixUp(node: BinaryNode<E>) {
        /*
RB-INSERT-FIXUP(T, z)
while color[p[z]] = RED                                                  // 若“当前节点(z)的父节点是红色”，则进行以下处理。
    do if p[z] = left[p[p[z]]]                                           // 若“z的父节点”是“z的祖父节点的左孩子”，则进行以下处理。
          then y ← right[p[p[z]]]                                        // 将y设置为“z的叔叔节点(z的祖父节点的右孩子)”
               if color[y] = RED                                         // Case 1条件：叔叔是红色
                  then color[p[z]] ← BLACK                    ▹ Case 1   //  (01) 将“父节点”设为黑色。
                       color[y] ← BLACK                       ▹ Case 1   //  (02) 将“叔叔节点”设为黑色。
                       color[p[p[z]]] ← RED                   ▹ Case 1   //  (03) 将“祖父节点”设为“红色”。
                       z ← p[p[z]]                            ▹ Case 1   //  (04) 将“祖父节点”设为“当前节点”(红色节点)
                  else if z = right[p[z]]                                // Case 2条件：叔叔是黑色，且当前节点是右孩子
                          then z ← p[z]                       ▹ Case 2   //  (01) 将“父节点”作为“新的当前节点”。
                               LEFT-ROTATE(T, z)              ▹ Case 2   //  (02) 以“新的当前节点”为支点进行左旋。
                          color[p[z]] ← BLACK                 ▹ Case 3   // Case 3条件：叔叔是黑色，且当前节点是左孩子。(01) 将“父节点”设为“黑色”。
                          color[p[p[z]]] ← RED                ▹ Case 3   //  (02) 将“祖父节点”设为“红色”。
                          RIGHT-ROTATE(T, p[p[z]])            ▹ Case 3   //  (03) 以“祖父节点”为支点进行右旋。
       else (same as then clause with "right" and "left" exchanged)      // 若“z的父节点”是“z的祖父节点的右孩子”，将上面的操作中“right”和“left”交换位置，然后依次执行。
color[root[T]] ← BLACK
         */

        var z = node
        while (z.parent!!.color == RED) {
            if (z.parent == z.parent!!.parent!!.left) {
                var y = z.parent!!.parent!!.right
                if (y!!.color == RED) {
                    z.parent!!.color = BLACK
                    y.color = BLACK
                    z.parent!!.parent!!.color = RED
                    z = z.parent!!.parent!!
                } else if (z == z.parent!!.right) {

                }
            }
        }


    }


    override fun insert(ele: E) {
        insert(BinaryNode(ele))
    }

    override fun findMax(): E {
        checkSize()
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
        checkSize()
        return findMin(root!!).ele
    }

    private fun checkSize() {
        if (isEmpty()) {
            throw ArithmeticException()
        }
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

    private class BinaryNode<E : Comparable<E>>
    constructor(
            var ele: E,
            var color: Boolean = RED,
            var parent: BinaryNode<E>? = null, // 父结点
            var left: BinaryNode<E>? = null,// 左节点
            var right: BinaryNode<E>? = null // 右节点
    )

    companion object {
        const val BLACK = true
        const val RED = false
    }

}
