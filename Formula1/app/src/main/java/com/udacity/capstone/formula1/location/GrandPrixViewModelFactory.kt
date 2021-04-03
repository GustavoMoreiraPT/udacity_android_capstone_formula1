package com.udacity.capstone.formula1.location

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.udacity.capstone.formula1.database.Repository
import com.udacity.capstone.formula1.main.MainViewModel

class GrandPrixViewModelFactory(
    private val dataSource: Repository,
    private val application: Application
) : ViewModelProvider.Factory {

    @RequiresApi(Build.VERSION_CODES.O)
    @Suppress("unchecked_cast")
    override fun <T: ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(GrandPrixViewModel::class.java)){
            return GrandPrixViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}