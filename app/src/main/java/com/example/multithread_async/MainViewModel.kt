package com.example.multithread_async

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class MainViewModel : ViewModel() {
    val randomNumLD = MutableLiveData<List<Int>>()
    private var thread: Thread? = null
    private var isThreadRunning: Boolean = false

    init {
        getRandomNumber()
    }

    private fun getRandomNumber() {
        if (!isThreadRunning) {
            thread = Thread {
                while (isThreadRunning) {
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
            }
            thread?.start()
            isThreadRunning = true
        }
    }

    override fun onCleared() {
        super.onCleared()
        thread?.interrupt()
        isThreadRunning = false

    }
}
