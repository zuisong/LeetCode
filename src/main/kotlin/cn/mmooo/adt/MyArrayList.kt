package cn.mmooo.adt

class MyArrayList<E> : Collection<E> {

    var objeces: Array<Any?> = Array(size = 10, init = { null })


    fun add(element: E) {

    }

    fun removeByIndex() {

    }

    fun remove(element: E) {

    }

    override val size: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun contains(element: E): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun containsAll(elements: Collection<E>): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isEmpty(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun iterator(): Iterator<E> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}