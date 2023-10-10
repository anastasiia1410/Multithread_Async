package com.example.multithread_async

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainViewModel : ViewModel() {
    val randomNumFlow = MutableStateFlow<List<Int>>(emptyList())


    init {
        getRandomNumber()
    }

    private fun getRandomNumber() {
        viewModelScope.launch(Dispatchers.IO) {
            while (true) {
                val numberList = randomNumFlow.value.toMutableList()
                val number = Random.nextInt(1000)
                numberList.add(number)
                randomNumFlow.value = numberList
                delay(1000)
            }
        }
    }
}