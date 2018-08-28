package cn.mmooo.leetcode


fun main(args: Array<String>) {

}

/**
 * Definition for singly-linked list.
 */
class ListNode(var `val`: Int = 0) {
    var next: ListNode? = null
}

/**
Merge two sorted linked lists and return it as a new list. The new list
should be made by splicing together the nodes of the first two lists.

Example:

Input: 1->2->4, 1->3->4
Output: 1->1->2->3->4->4
 */
fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {

    val firstNode: ListNode = ListNode(0)

    var addNode = firstNode

    var n1 = l1
    var n2 = l2

    fun append(n: ListNode?) {
        addNode.next = n
        addNode = n!!
    }

    while (n1 != null || n2 != null) {
        if (n1 != null && (n2 == null || n1.`val` > n2.`val`)) {
            append(n1)
            n1 = n1.next
        } else {
            append(n2)
            n2 = n2!!.next
        }

    }
    return firstNode.next
}