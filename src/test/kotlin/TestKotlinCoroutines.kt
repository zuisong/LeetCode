import kotlinx.coroutines.*
import java.lang.Thread.*
import java.time.*
import java.util.concurrent.*
import kotlin.system.*


fun main() {
    test1()
    test2()
}


fun test1() = runBlocking {
    measureTimeMillis {
        coroutineScope {
            repeat(10000) {
                launch {
                    delay(1000)
                    println("${LocalDateTime.now()} hello, $it")
                }
            }
        }
    }.let(::println)
}

fun test2() {
    val threadPool = Executors.newCachedThreadPool()

    measureTimeMillis {
        repeat(10000) {
            threadPool.submit {
                sleep(1000)
                println("${LocalDateTime.now()} hello, $it")
            }
        }
    }.let(::println)
}
