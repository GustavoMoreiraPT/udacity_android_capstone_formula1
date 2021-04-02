package com.udacity.capstone.formula1.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.udacity.capstone.formula1.dto.Picture

@RequiresApi(Build.VERSION_CODES.O)
class MainViewModel() {

    private val _constructorOfWeek = MutableLiveData<Picture>()
    val constructorOfWeek: LiveData<Picture>
        get() = _constructorOfWeek

    private val _driverOfWeek = MutableLiveData<Picture>()
    val driverOfWeek: LiveData<Picture>
        get() = _driverOfWeek
}