package cn.mmooo.leetcode

/**
给定一个二叉树，重新排列树，使树中的最小值现在是树的根结点，并且每个结点没有左子结点，只有一个右子结点。



示例 ：

输入：[5,3,6,2,4,null,8,1,null,null,null,7,9]

5
/ \
3    6
/ \    \
2   4    8
/        / \
1        7   9

输出：[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]

1
\
2
\
3
\
4
\
5
\
6
\
7
\
8
\
9
 */
fun increasingBST(root: TreeNode?): TreeNode? {

    if (root == null) return null

    fun pollLeft(n: TreeNode): TreeNode {

        if (n.right != null) {
            n.right = pollLeft(n.right!!)
        }

        if (n.left == null) {
            return n
        } else {
            val node = pollLeft(n.left!!)
            n.left = null

            var temp = node
            while (temp.right != null) {
                temp = temp.right!!
            }
            temp.right = n

            return node
        }
    }
    return pollLeft(root)

}

fun main(args: Array<String>) {
    val node1 = TreeNode(1)
    val node2 = TreeNode(2)
    val node3 = TreeNode(3)
    val node4 = TreeNode(4)
    val node5 = TreeNode(5)

    node3.left = node2
    node2.left = node1
    node4.left = node3
    node4.right = node5

    val node = increasingBST(node4)
    node.toString()


}