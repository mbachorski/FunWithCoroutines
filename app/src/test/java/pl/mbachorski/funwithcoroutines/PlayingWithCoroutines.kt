package pl.mbachorski.funwithcoroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class PlayingWithCoroutines {
    @Test
    fun simpleCoroutine() {
        assertEquals(runSimpleCoroutine(), "Hello,World!")
    }

    private fun runSimpleCoroutine(): String {
        var text = ""
        GlobalScope.launch {
            // launch new coroutine in background and continue
            delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
            println("World!") // print after delay
            text += "World!"
        }
        println("Hello,") // main thread continues while coroutine is delayed
        text += "Hello,"
        Thread.sleep(2000L) // block main thread for 2 seconds to keep JVM alive
        return text
    }

    @Test
    fun runBlockingCoroutine() = runBlocking {
        GlobalScope.launch {
            // launch new coroutine in background and continue
            delay(1000L)
            println("World!")
        }
        println("Hello,") // main coroutine continues here immediately
        delay(2000L)      // delaying for 2 seconds to keep JVM alive
    }

}
