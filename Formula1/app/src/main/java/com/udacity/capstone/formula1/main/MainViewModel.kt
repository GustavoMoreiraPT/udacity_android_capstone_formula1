package com.udacity.capstone.formula1.main

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.udacity.capstone.formula1.database.Repository
import com.udacity.capstone.formula1.dto.Constructor
import com.udacity.capstone.formula1.dto.Driver
import com.udacity.capstone.formula1.dto.Picture
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
class MainViewModel(val database: Repository, application: Application) : AndroidViewModel(application) {

    val constructorOfWeek = "https://www.iol.pt/multimedia/oratvi/multimedia/imagem/id/5e44106d0cf207193069599b/1024.jpg"

    val driverOfWeek = "https://www.f1mania.net/wp-content/uploads/2021/01/SI202101200077_news-crop-1612187917-2000x684.jpg"

    init {
        displayConstructors()
        displayDrivers()
    }

    val constructors = database.allConstructors

    val drivers = database.allDrivers

    private val _navigateToConstructorDetail = MutableLiveData<Constructor?>()

    val navigateToConstructorDetail: LiveData<Constructor?>
        get() = _navigateToConstructorDetail

    fun onConstructorClicked(constructor: Constructor) {
        _navigateToConstructorDetail.value = constructor
    }

    fun onConstructorNavigated() {
        _navigateToConstructorDetail.value = null
    }

    private val _navigateToDriverDetail = MutableLiveData<Driver?>()

    val navigateToDriverDetail: LiveData<Driver?>
        get() = _navigateToDriverDetail

    fun onDriverClicked(driver: Driver) {
        _navigateToDriverDetail.value = driver
    }

    fun onDriverNavigated() {
        _navigateToDriverDetail.value = null
    }

    private fun displayConstructors() {
        viewModelScope.launch {
            database.updateConstructors()
        }
    }

    private fun displayDrivers() {
        viewModelScope.launch {
            database.updateDrivers()
        }
    }
}