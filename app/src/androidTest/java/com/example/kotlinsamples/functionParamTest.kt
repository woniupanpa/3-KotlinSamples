package com.example.kotlinsamples

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

/**
 *
 *@Author Yanjim
 *@Date 2022/6/1
 */
@RunWith(AndroidJUnit4::class)
class functionParamTest {
    val TAG = "ExampleInstrumentedTest"
    @Test
    fun functionParamHiTest(){
        peopleHi("Android"){
            sayHi("world")
        }
    }

    fun sayHi(msg:String){
        Log.d(TAG, "hi....$msg")
    }

    fun peopleHi(name:String, hello: (name:String) -> Unit){
        hello(name)
    }

    @Test
    fun functionParamTest(){
        people ({ say() })
        //在 kotlin 中有一个约定，如果最后一个参数是函数，可以省略括号
        people { say() }
    }

    fun say() {
        Log.d(TAG,"Hello World")
    }

    fun people(hello: () -> Unit) {
        hello()
    }
}