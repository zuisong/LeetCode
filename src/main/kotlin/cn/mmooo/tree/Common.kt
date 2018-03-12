package cn.mmooo.tree

internal data class Node<T : Comparable<T>>(
        val value: T,
        var left: Node<T>?,
        var rght: Node<T>?
) {
    constructor(value: T) : this(value, null, null)

    override fun toString(): String {
        return "Node(value= $value , left= [ $left ], rght= [ $rght ])"
    }

}