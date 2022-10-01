package com.example.kotlinsamples

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith

/**
 *
 *@Author Yanjim
 *@Date 2022/6/1
 */
@RunWith(AndroidJUnit4::class)
class ChannelTest {
    val TAG = "ExampleInstrumentedTest"
    @Test
    fun kotlinChannelTest(){
        runBlocking {
            val channel = Channel<Int>()
            launch {
                (1..3).forEach{
                    Log.d(TAG, "send$it")
                    channel.send(it)
                }
                channel.close()
            }
            /*launch {
                *//*for(i in channel){
                Log.d("test", "receive$i")
            }*//*
            repeat(3){
                Log.d("test", "receive ${channel.receive()}");
            }
        }*/

            repeat(3){
                Log.d(TAG, "receive ${channel.receive()}");
            }

            Log.d(TAG, "结束")
        }
    }
}