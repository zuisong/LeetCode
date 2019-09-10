package cn.mmooo.adt

open class SkipList<T : Comparable<T>> : MutableCollection<T> {

    private var currentSize = 0


    override fun contains(element: T): Boolean {
        return findNodeOfEle(element) != null
    }

    override fun containsAll(elements: Collection<T>): Boolean {
        return elements.all(::contains)
    }

    override fun isEmpty(): Boolean = size == 0

    override fun addAll(elements: Collection<T>): Boolean = elements.all(::add)

    override fun clear() {
        root = null
    }

    private fun findNodeOfEle(element: T): Node<T>? {
        var node = root
        while (node != null) {
            if (node.value == element) {
                return node
            }
            // 从左边界往右找，在节点的左边，则直接返回找不到节点
            if (node.value > element) {
                return null
            }
            // 右边节点为null 表示到了右边界，还没找到直接报不存在
            if (node.right == null) {
                return null
            }
            node = if (node.right!!.value > element) {
                node.right
            } else {
                node.down
            }
        }
        return null

    }

    override fun remove(element: T): Boolean {
        val nodeOfEle = findNodeOfEle(element) ?: return false
        var node = nodeOfEle
        while (node.down != null) {
            node = node.down!!
        }

        removeNode(node)
        currentSize--
        return true
    }

    /**
     * @param node 一节链路中的最下面的那个节点
     */
    private fun removeNode(node: Node<T>) {

        if (size == 1) {
            root = null
            return
        }
        /**
         * size=2 的情况要特殊处理，否则会死循环
         */
        if (size == 2) {
            if (node.value == root!!.value) {
                root = root!!.right
            }
            root!!.left = null
            root!!.down = null
            root!!.right = null
            return
        }

        if (node.left != null && node.right != null) {
            /** 中间节点
             *  直接把左右节点连起来
             */
            var toRemoveNode: Node<T> = node
            while (true) {
                toRemoveNode.left!!.right = toRemoveNode.right!!
                toRemoveNode.right!!.left = toRemoveNode.left!!
                toRemoveNode = toRemoveNode.up ?: return
            }
        } else if (node.left == null) {
            /**
             * 头结点
             * 删除策略是  先把旁边节点的值拷贝到头结点来  然后删除旁边节点
             */
            var headNode: Node<T> = node
            while (true) {
                headNode.value = node.right!!.value
                headNode = headNode.up ?: break
            }
            removeNode(node.right!!)


        } else if (node.right == null) {
            /**
             * 尾节点
             * 删除策略是  先把旁边节点的值拷贝到尾结点来  然后删除旁边节点
             */
            var tailNode: Node<T> = node
            while (true) {
                tailNode.value = node.left!!.value
                tailNode = tailNode.up ?: break
            }
            removeNode(node.left!!)
        }

    }

    override fun removeAll(elements: Collection<T>): Boolean = elements.all(::remove)


    override fun retainAll(elements: Collection<T>): Boolean {
        var modified = false
        val it = iterator()
        while (it.hasNext()) {
            if (!elements.contains(it.next())) {
                it.remove()
                modified = true
            }
        }
        return modified
    }

    override val size: Int
        get() = currentSize

    override fun add(element: T): Boolean {
        val d = fun(ele: T): Node<T>? {

            var node = root
            while (node != null) {
                if (node.value == ele) {
                    println("重复元素,怎么处理")
                    return null
                }
                // 从左边界往右找，在节点的左边，则直接返回找不到节点
                if (node.value > ele) {
                    return node
                }
                // 右边节点为null 表示到了右边界，还没找到直接报不存在
                if (node.right == null) {
                    return null
                }
                node = if (node.right!!.value > ele) {
                    node.right
                } else {
                    node.down
                }
            }
            return null
        }
        val node = d(element)
        return false


    }


    override fun iterator(): MutableIterator<T> {
        return object : MutableIterator<T> {
            private var tempNode: Node<T>?
            private var preNode: Node<T>?

            init {
                tempNode = root?.bottom()
                preNode = null
            }

            override fun hasNext(): Boolean =
                    tempNode != null

            override fun next(): T {
                val t = tempNode!!.value
                preNode = tempNode
                tempNode = tempNode!!.right
                return t
            }

            override fun remove() {
                removeNode(preNode!!)
            }

        }

    }

    private var root: Node<T>? = null


    companion object {

        class Node<T : Comparable<T>>(
                var value: T,
                var currentLevel: Int = 0
        ) {
            var up: Node<T>? = null
            var down: Node<T>? = null
            var left: Node<T>? = null
            var right: Node<T>? = null
        }

        fun <T : Comparable<T>> Node<T>.bottom(): Node<T> {
            var res = this
            while (res.down != null) {
                res = res.down!!
            }
            return res
        }

        fun <T : Comparable<T>> Node<T>.top(): Node<T> {
            var res = this
            while (res.up != null) {
                res = res.up!!
            }
            return res
        }
    }

}
