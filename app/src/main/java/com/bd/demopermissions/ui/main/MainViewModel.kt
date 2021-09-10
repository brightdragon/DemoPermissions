package com.bd.demopermissions.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _permissionEvent = MutableLiveData<Unit>()
    val permissionEvent: LiveData<Unit> = _permissionEvent

    fun requestPermission(){
        _permissionEvent.value = Unit
    }
}