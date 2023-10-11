package com.example.multithread_async

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class MainViewModel : ViewModel() {
    val randomNumLD = MutableLiveData<List<Int>>()
    private val compositeDisposable = CompositeDisposable()

    init {
        getRandomNumber()
    }

    private fun getRandomNumber() {
        val disposable = Observable.interval(1, TimeUnit.SECONDS)
            .map { Random.nextInt(1000) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { numbers ->
                val numberList = randomNumLD.value?.toMutableList() ?: mutableListOf()
                numberList.add(numbers)
                randomNumLD.value = numberList
            }

        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}