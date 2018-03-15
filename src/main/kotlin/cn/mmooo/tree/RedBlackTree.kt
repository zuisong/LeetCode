package cn.mmooo.tree

import java.util.*

fun main(args: Array<String>) {
    val tree = Tree<Int>()
    repeat(5) {

        tree.add(Random().nextInt(10))
    }

    println(tree)
}

internal class Tree<E : Comparable<E>> {

    override fun toString(): String {
        return rootNode.toString()
    }

    fun add(element: E) {

        if (rootNode == null) {
            rootNode = Node(element)
        } else {
            var n = rootNode
            while (true) {
                if (n!!.value > element) {
                    if (n.rght == null) {
                        n.rght = Node(element)
                        break
                    } else {
                        n = n.rght
                    }
                } else {
                    if (n.left == null) {
                        n.left = Node(element)
                        break
                    } else {
                        n = n.left
                    }
                }
            }
        }
        this.size++
    }

    var rootNode: Node<E>? = null
    var size: Int = 0


}