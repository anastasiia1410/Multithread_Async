package com.example.multithread_async

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class MainViewModel : ViewModel() {
    val handler = Handler(Looper.getMainLooper())
    val randomNumLD = MutableLiveData<List<Int>>()

    init {
        getRandomNumber()
    }

    private fun getRandomNumber() {
        handler.post(object : Runnable {
            override fun run() {
                val numberList = randomNumLD.value?.toMutableList() ?: mutableListOf()
                val number = Random.nextInt(1000)
                numberList.add(number)
                randomNumLD.value = numberList
                handler.postDelayed(this, 1000)
            }
        })
    }
}