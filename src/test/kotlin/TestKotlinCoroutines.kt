import kotlinx.coroutines.experimental.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) = runBlocking {

    val threadNum = 5

    val time: Long = 100
    measureTimeMillis {
    val list1 = List(threadNum) {
        async(CommonPool) {
            println(1)
            delay(time, TimeUnit.MILLISECONDS)
        }
    }
    Thread.sleep(200)
println(2)
println(3)
        list1.forEach { it.join() }
    }.let(::println)

//
//    val list2 = List(threadNum) {
//        Executors.callable {
//            Thread.sleep(time)
//        }
//    }
//
//    measureTimeMillis {
//        val threadPool = Executors.newCachedThreadPool()
//        threadPool.invokeAll(list2)
//        threadPool.shutdown()
//    }.let(::println)

}