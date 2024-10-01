package com.example.technicalknowledgesharing

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    fun launchTask() {
        Log.d("MainViewModel", "Start")
        viewModelScope.launch {
            delay(2000)
            Log.d("MainViewModel", "Running...")
        }
        Log.d("MainViewModel", "End")
    }

    fun asyncTask() {
        Log.d("MainViewModel", "Start")
        viewModelScope.launch {
            val value1 = async {
                Log.d("MainViewModel", "Value 1 started")
                delay(2000)
                Log.d("MainViewModel", "Value 1 End")
                10
            }
            val value2 = async {
                Log.d("MainViewModel", "Value 2 started")
                delay(1000)
                Log.d("MainViewModel", "Value 2 End")
                20
            }
            launch {
                val result1 = value1.await()
                val result2 = value2.await()
                Log.d("MainViewModel", "Sum: $result1")
                Log.d("MainViewModel", "Sum: $result2")
//                Log.d("MainViewModel", "Sum: ${result1 + result2}")
            }
        }
        Log.d("MainViewModel", "End")
    }

    fun runBlockingTasks() {
        Log.d("MainViewModel", "Start")
        runBlocking {
            delay(5000)
            Log.d("MainViewModel", "Running.....")
        }
        Log.d("MainViewModel", "End")
    }

    fun withConTextDemo() {
        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//                Log.d("MainViewModel", "Running on ${Thread.currentThread().name}")
//            }
//            withContext(Dispatchers.Main) {
//                Log.d("MainViewModel", "Running on ${Thread.currentThread().name}")
//            }
            withContext(Dispatchers.Default) {
                Log.d("MainViewModel", "Running on ${Thread.currentThread().name}")
                withContext(Dispatchers.Main) {
                    Log.d("MainViewModel", "Running on ${Thread.currentThread().name}")
                }
            }
        }
    }
}