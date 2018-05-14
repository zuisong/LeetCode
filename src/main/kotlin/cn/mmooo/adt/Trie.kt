package cn.mmooo.adt


/**
Q208
实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。

注意:
你可以假设所有的输入都是由小写字母 a-z 构成的。
 */
class Trie() {
    class TrieNode(
            var isWord: Boolean = false,
            var map: HashMap<Char, TrieNode> = HashMap()
    )

    val root: TrieNode = TrieNode()

    /** Initialize your data structure here. */


    /** Inserts a word into the trie. */
    fun insert(word: String) {
        if (word.isEmpty()) return
        insert(root, word)
    }

    private fun insert(node: TrieNode, word: String) {
        val trie = node.map[word.first()]
        if (trie == null) {
            val node1 = TrieNode(word.length == 1)
            node.map[word.first()] = node1
        } else {
            trie.isWord = trie.isWord || word.length == 1
        }
        if (word.length > 1) {
            insert(node.map[word.first()]!!, word.substring(1))
        }
    }

    /** Returns if the word is in the trie. */
    fun search(word: String): Boolean {
        fun search(node: TrieNode, s: String): Boolean {
            return if (s.length > 1)
                node.map[s.first()] != null && search(node.map[s.first()]!!, s.substring(1))
            else node.map[s.first()]?.isWord ?: false
        }

        return search(root, word)
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    fun startsWith(prefix: String): Boolean {
        fun startsWith(node: TrieNode, pre: String): Boolean {
            return if (pre.isNotEmpty())
                node.map[pre.first()] != null && startsWith(node.map[pre.first()]!!, pre.substring(1))
            else true
        }

        return startsWith(root, prefix)
    }

}

/**
 * Your Trie object will be instantiated and called as such:
 * var obj = Trie()
 * obj.insert(word)
 * var param_2 = obj.search(word)
 * var param_3 = obj.startsWith(prefix)
 */

fun main(args: Array<String>) {
    val trie = Trie()
    trie.insert("abc")
    trie.search("abc")
    trie.search("ab")
    trie.insert("ab")
    trie.insert("ck")
    trie.insert("ak")
    println(trie.search("ckj"))
    println(trie.startsWith("b"))

}