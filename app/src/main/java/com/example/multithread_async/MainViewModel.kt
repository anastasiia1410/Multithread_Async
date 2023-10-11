package com.example.multithread_async

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class MainViewModel : ViewModel() {
    val randomNumLD = MutableLiveData<List<Int>>()

    init {
        getRandomNumber()
    }

    private fun getRandomNumber() {
        Thread {
            while (true) {
                val numberList = randomNumLD.value?.toMutableList() ?: mutableListOf()
                val number = Random.nextInt(1000)
                numberList.add(number)
                randomNumLD.postValue(numberList)

                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    return@Thread
                }
            }
        }.start()
    }
}
