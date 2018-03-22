package cn.mmooo.q6

/**
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
 */
fun main(args: Array<String>) {
    Solution().convert("AB", 1)
            .let { println(it) }
}

class Solution {
    fun convert(s: String, numRows: Int): String {

        if(numRows==1){
            return s
        }

        val arr = Array(numRows, { CharArray(s.length * 2, { '\t' }) })

        var indexOfS = 0
        var x = 0
        var y = 0
        var isUp = false

        while (true) {
            if (indexOfS >= s.length) {
                break
            }

            arr[y][x] = s[indexOfS]
            indexOfS++

            when (y) {
                0 -> isUp = false
                numRows - 1 -> isUp = true
            }

            if (isUp) {
                x += 1
                y -= 1
            } else {
                y += 1
            }


        }

        val result = StringBuilder()

        arr.forEach {
            it.filter { it != '\t' }.joinToString("").let {
                result.append(it)
            }
        }

        return result.toString()
    }
}