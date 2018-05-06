package cn.mmooo.adt


class QueueAndStack<AnyType> internal constructor() : Queue<AnyType>, Stack<AnyType>, Iterable<AnyType> {
    override fun peekFirst(): AnyType {
        checkSize()
        return head.next!!.ele!!
    }

    override fun peekLast(): AnyType {
        checkSize()
        return tail.pre!!.ele!!
    }


    override fun iterator(): Iterator<AnyType> {
        return object : Iterator<AnyType> {
            private var currentIterNode = head

            override fun hasNext(): Boolean {
                return currentIterNode.next !== tail
            }

            override fun next(): AnyType {
                currentIterNode = currentIterNode.next!!
                return currentIterNode.ele!!
            }
        }
    }

    private var size: Int = 0
    private var head: Node<AnyType>
    private var tail: Node<AnyType>

    fun makeEmpty() {
        head = Node()
        tail = Node()
        size = 0
        head.next = tail
        tail.pre = head
    }

    init {
        head = Node()
        tail = Node()
        size = 0
        head.next = tail
        tail.pre = head
    }

    private fun checkSize() {
        if (isEmpty()) {
            throw IllegalArgumentException("")
        }
    }

    override fun poll(): AnyType {
        checkSize()

        val t = head.next!!.ele!!
        head.next!!.next!!.pre = head
        head.next = head.next!!.next
        this.size--
        return t
    }

    override fun push(e: AnyType) {
        val node = Node(e)
        tail.pre!!.next = node
        node.pre = tail.pre
        node.next = tail
        tail.pre = node
        this.size++
    }

    override fun size(): Int {
        return this.size
    }

    override fun pop(): AnyType {
        checkSize()
        val t = tail.pre!!.ele
        tail.pre!!.pre!!.next = tail
        tail.pre = tail.pre!!.pre
        this.size--
        return t!!
    }

    fun isEmpty(): Boolean {
        return size() == 0
    }

    private class Node<T> {
        var next: Node<T>? = null
        var pre: Node<T>? = null
        var ele: T? = null

        internal constructor()

        internal constructor(ele: T) {
            this.ele = ele
        }
    }
}

fun main(args: Array<String>) {

}
