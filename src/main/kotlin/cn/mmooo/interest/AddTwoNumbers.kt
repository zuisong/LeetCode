import java.util.*

/**
Definition for singly-linked list.
 */
//private class ListNode(var `val`: Int = 0) {
//    var next: ListNode? = null
//}
// 正序 3->6 表示36
fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {


    val s1 = Stack<Int>()
    val s2 = Stack<Int>()
    var ll1 = l1
    var ll2 = l2
    while (ll1 != null) {
        s1.push(ll1.`val`)
        ll1 = ll1.next
    }

    fun <T> Stack<T>.popOrElse(t: T): T {
        return if (this.isNotEmpty()) {
            this.pop()
        } else {
            t
        }
    }

    while (ll2 != null) {
        s2.push(ll2.`val`)
        ll2 = ll2.next
    }

    var jinwei: Int = 0
    var lastNode: ListNode? = null
    while (s1.isNotEmpty() || s2.isNotEmpty()) {
        val sum = s1.popOrElse(0) + s2.popOrElse(0) + jinwei
        jinwei = sum / 10
        val thisNode = ListNode(sum % 10)
        thisNode.next = lastNode
        lastNode = thisNode
    }

    if (jinwei != 0) {
        val thisNode = ListNode(jinwei)
        thisNode.next = lastNode
        lastNode = thisNode
    }

    println(lastNode)
    return lastNode
}

//倒序 3->6 表示 63
fun addTwoNumbers1(ll1: ListNode?, ll2: ListNode?): ListNode? {

    var l1 = ll1
    var l2 = ll2;

    var jinwei = 0
    var lastNode: ListNode? = null

    var firstNode = ListNode(0)

    while (l1 != null || l2 != null) {
        val sum = (l1?.`val` ?: 0) + (l2?.`val` ?: 0) + jinwei
        jinwei = sum / 10
        val thisNode = ListNode(sum % 10)
        if (lastNode != null) {
            lastNode.next = thisNode
        } else {
            firstNode = thisNode
        }
        lastNode = thisNode
        l1 = l1?.next
        l2 = l2?.next
    }
    if (jinwei != 0) {
        lastNode!!.next = ListNode(jinwei)
    }

    return firstNode
}

fun main(args: Array<String>) {
    val l1 = ListNode(5)
    l1.next = ListNode(6)

    val l2 = ListNode(3)
    l2.next = ListNode(9)
    addTwoNumbers(l1, l2)

}


// Definition for singly-linked list.
class ListNode(var `val`: Int = 0) {
    var next: ListNode? = null
    override fun toString(): String {
        return "$`val` -> ${next.toString()}"
    }
}
