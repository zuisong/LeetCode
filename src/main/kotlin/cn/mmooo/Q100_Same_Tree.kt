package cn.mmooo.q100


/**
Given two binary trees, write a function to check if they are the same or not.
Two binary trees are considered the same if they are structurally identical and
the nodes have the same value.

Example 1:
Input:     1         1
/ \       / \
2   3     2   3

[1,2,3],   [1,2,3]

Output: true
 */
fun main(args: Array<String>) {

}


/**
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int = 0) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
class TreeNode(var `val`: Int = 0) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Solution {
    fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
        return when {
            p == null && q == null -> true
            p == null || q == null -> false
            else -> p.`val` == q.`val` && isSameTree(p.left, q.left) && isSameTree(p.right, q.right)
        }
    }
}
