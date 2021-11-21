package com.mj.mvvmpatternframe.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private var count = 0
    private val _countText: MutableLiveData<String> = MutableLiveData()
    val countText: LiveData<String> get() = _countText

    init {
        _countText.value = "click count : $count"
    }

    fun clickIncreaseButton() {
        _countText.value = "click count : ${++count}"
    }

}