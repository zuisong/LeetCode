package cn.mmooo.leetcode

/**
序列化是将数据结构或对象转换为一系列位的过程，以便它可以存储在文件或内存缓冲区中，或通过网络连接链路传输，以便稍后在同一个或另一个计算机环境中重建。

设计一个算法来序列化和反序列化二叉搜索树。 对序列化/反序列化算法的工作方式没有限制。 您只需确保二叉搜索树可以序列化为字符串，并且可以将该字符串反序列化为最初的二叉搜索树。

编码的字符串应尽可能紧凑。

注意：不要使用类成员/全局/静态变量来存储状态。 你的序列化和反序列化算法应该是无状态的。
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */

class Codec {
    /**
     * 思路: 分隔符前面用一个三元组a,b,c保存数据
     *  a 表示 节点的值
     *  b 表示 分隔符后的 b 个字符存的是左节点的数据
     *  c 表示 左节点数据后的 c 个字符存的是右节点的数据
     *
     *  @see serialize
     *  序列化的时候，递归
     *
     *  @see deserialize
     *  反序列化的时候先拿到三元组数据，然后分割字符串拿到左右节点的字符串
     *  递归
     */
    /**
     *  分隔符
     */
    private val delimiter = "|"

    // Encodes a tree to a single string.
    fun serialize(root: TreeNode): String {
        fun s(node: TreeNode?): String {
            if (node == null) {
                return ""
            }
            val leftData = s(node.left)
            val rightData = s(node.right)
            return "${node.`val`},${leftData.length},${rightData.length}$delimiter$leftData$rightData"
        }
        return s(root)
    }

    // Decodes your encoded data to tree.
    fun deserialize(data: String): TreeNode? {
        if (data.isEmpty()) return null
        val idx = data.indexOf(delimiter)
        val s = data.substring(0, idx)
        val list = s.split(',').map { it.toInt() }

        val leftData = data.substring(idx + 1, idx + 1 + list[1])

        val left = deserialize(leftData)
        val rightData = data.substring(idx + 1 + list[1], idx + 1 + list[1] + list[2])
        val right = deserialize(rightData)
        val root = TreeNode(list[0])
        root.left = left
        root.right = right
        return root
    }
}


fun main(args: Array<String>) {
    val root = TreeNode(5)
    val left1 = TreeNode(4)
    val left2 = TreeNode(1)
    left1.right = left2
    root.left = left1
    root.right = TreeNode(3)
    val codec = Codec()
    val s = codec.serialize(root)
    s.let(::println)
    val node = codec.deserialize(s)
    val b = root == node
    println(b)
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));