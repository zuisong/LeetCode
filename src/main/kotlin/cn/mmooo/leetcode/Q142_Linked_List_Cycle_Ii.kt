package cn.mmooo.leetcode

/**
 * Definition for singly-linked list.
 * class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) {
 * val = x;
 * next = null;
 * }
 * }
 */
/**
给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
说明：不允许修改给定的链表。
进阶：
你是否可以不用额外空间解决此题？
 */

/**
 * @see <a href="https://blog.csdn.net/wuzhekai1985/article/details/6725263">思路解析</a>
 */
fun detectCycle(head: ListNode): ListNode? {
    var s1 = head
    var s2 = head
    var step = 0
    while (true) {
        s1 = s1.next?.next ?: return null
        s2 = s2.next!!
        step += 1
        if (s1 === s2) break
    }

    s1 = head

    while (s1 !== s2) {
        s1 = s1.next!!
        s2 = s2.next!!
    }

    return s1
}


fun main(args: Array<String>) {
    val n1 = ListNode(1)
    val n2 = ListNode(2)
    val n3 = ListNode(3)
    val n4 = ListNode(4)
    val n5 = ListNode(5)
    val n6 = ListNode(6)
    n1.next = n2
    n2.next = n3
    n3.next = n4
    n4.next = n5
    n5.next = n6
    n6.next = n5
    val cycle = detectCycle(n1)
    println(cycle?.`val`)
}
