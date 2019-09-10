/**
 * Created by ice1000 on 2017/4/27.
 *
 * @author ice1000
 */

fun <A, B, C : Any> zipWith(op: (A, B) -> C) = { x: Sequence<A> ->
    { y: Sequence<B> ->
        val iX = x.iterator()
        val iY = y.iterator()
        generateSequence {
            if (iX.hasNext() and iY.hasNext()) op(iX.next(), iY.next())
            else null
        }
    }
}

private fun <T : Any> generate() = zipWith { x: Int, y: T -> "[$y * x^$x]" }(
        generateSequence(0, Int::inc)
)

fun main(args: Array<String>) {
    generate<Int>()(sequenceOf(1, 1, 2, 3, 5, 8, 13, 21)).forEach(::println)
}
