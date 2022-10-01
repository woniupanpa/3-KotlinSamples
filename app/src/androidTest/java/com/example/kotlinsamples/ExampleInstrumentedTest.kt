package com.example.kotlinsamples

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.*
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    val TAG = "ExampleInstrumentedTest"

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.kotlinsamples", appContext.packageName)
    }

    @Test
    fun asyncTest() {
        runBlocking {
            val one = async { doSomethingUsefulOne() }
            val two = async { doSomethingUsefulTwo() }
            Log.d(TAG, "The answer is ${one.await() + two.await()}");
            Log.d(TAG, "Complete in s");
        }
    }

    suspend fun doSomethingUsefulOne(): Int {
        delay(1000)
        Log.d(TAG, "doSomethingUsefulOne thread ${Thread.currentThread().name}")
        return 13
    }

    suspend fun doSomethingUsefulTwo(): Int {
        delay(1000)
        Log.d(TAG, "doSomethingUsefulTwo thread ${Thread.currentThread().name}")
        return 29
    }

    @Test
    fun jobTest() {
        runBlocking {
            val job = CoroutineScope(Dispatchers.Main).launch {
                try {
                    repeat(10) { i ->
                        Log.d(TAG, "job: I'm sleeping $i ...")
                        delay(500L)
                    }
                } finally {
                    Log.d(TAG, "I'm running finally")
                }

            }
            delay(1300L) // 延迟一段时间
            Log.d(TAG, "main: I'm tired of waiting!")
            job.cancel() // 取消该作业
            job.join() // 等待作业执行结束
            Log.d(TAG, "main: Now I can quit.")
        }
    }

    @Test
    fun globalLaunchTest() {
        GlobalScope.launch {
            Log.d(TAG, "GlobalScope ${Thread.currentThread().name}")
            delay(1000L)
            Log.d(TAG, "world")
        }
        Log.d(TAG, "main ${Thread.currentThread().name}")
        Log.d(TAG, "hello")
        Thread.sleep(2000)
    }

    @Test
    fun arrayTest() {
        val strs: Array<String> = arrayOf("a", "b", "c")
        Log.d(TAG, strs[0]);
        strs[0] = "d";
        Log.d(TAG, strs[0]);
    }

    @Test
    fun coroutineScopeTest(){
        runBlocking {
            Log.d(TAG,"test1")
            getUserInfo()
            Log.d(TAG,"test5")
        }
    }

    suspend fun getUserInfo():String{
        Log.d(TAG,"test2")
        withContext(Dispatchers.IO){
            Log.d(TAG,"test3" + Thread.currentThread().name)
            delay(5000)
        }
        Log.d(TAG,"test4")
        return "22"
    }

}