package cn.mmooo.adt.impl

class QueueAndStackByJava<AnyType> internal constructor() : Iterable<AnyType> {
    var size = 0
        private set
    private lateinit var head: Node<AnyType>
    private lateinit var tail: Node<AnyType>
    private fun init() {
        size = 0
        head = Node()
        tail = Node()
        head.next = tail
        tail.pre = head
    }

    fun makeEmpty() {
        init()
    }

    fun poll(): AnyType {
        checkSize()
        val t: AnyType = head.next!!.ele!!
        head.next!!.next!!.pre = head
        head.next = head.next!!.next
        size--
        return t!!
    }

    private fun checkSize() {
        require(!isEmpty) { "" }
    }

    fun push(anyType: AnyType) {
        val node = Node(anyType)
        tail!!.pre!!.next = node
        node.pre = tail!!.pre
        node.next = tail
        tail!!.pre = node
        size++
    }

    fun pop(): AnyType {
        checkSize()
        val t: AnyType = tail.pre!!.ele!!
        tail.pre!!.pre!!.next = tail
        tail.pre = tail.pre!!.pre
        size--
        return t
    }

    val isEmpty: Boolean
        get() = size == 0

    fun peekFirst(): AnyType {
        checkSize()
        return head.next!!.ele!!
    }

    fun peekLast(): AnyType {
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

    private class Node<T> {
        var next: Node<T>? = null
        var pre: Node<T>? = null
        var ele: T? = null

        internal constructor() {}
        internal constructor(ele: T) {
            this.ele = ele
        }
    }

    init {
        init()
    }
}