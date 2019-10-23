import java.util.concurrent.ForkJoinPool
import java.util.concurrent.RecursiveTask
import java.util.concurrent.RecursiveAction as RecursiveAction1

fun main() {
    val printTask = Action((1..200).toList().toTypedArray())
    val sumTask = SumTask((1..20).toList().toTypedArray())

    val pool = ForkJoinPool(10)
    val res = pool.submit(sumTask).get()
//    pool.shutdown()
    println(res)
}

class SumTask(private val arr: Array<Int>,
              private val start: Int = 0,
              private val end: Int = arr.lastIndex)
    : RecursiveTask<Int>() {
    override fun compute(): Int {

        if (start == end) {
            println(" ${Thread.currentThread()} ${arr[start]}")
            return arr[start]
        }
        val mid = (start + end) / 2;
        val subtask1 = SumTask(arr, start, mid);
        val subtask2 = SumTask(arr, mid + 1, end);
        invokeAll(subtask1, subtask2)
        return subtask1.join() + subtask2.join()
    }


}

class Action(
        private val arr: Array<Int>,
        private val start: Int = 0,
        private val end: Int = arr.lastIndex
) : RecursiveAction1() {
    override fun compute() {

        if (start == end) {
            println(" ${Thread.currentThread()} ${arr[start]}")
            return
        }
        val mid = (start + end) / 2;
        val subtask1 = Action(arr, start, mid);
        val subtask2 = Action(arr, mid + 1, end);
        invokeAll(subtask1, subtask2)
        subtask1.join()
        subtask2.join()
    }

}