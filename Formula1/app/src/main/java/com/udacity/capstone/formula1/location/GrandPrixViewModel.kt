package com.udacity.capstone.formula1.location

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.capstone.formula1.database.Repository
import com.udacity.capstone.formula1.database.toEntity
import com.udacity.capstone.formula1.dto.FavoriteLocation
import com.udacity.capstone.formula1.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class GrandPrixViewModel(val database: Repository, application: Application) : AndroidViewModel(application) {

    val favoriteLocations = database.allFavoriteLocations

    fun saveNewFavoriteLocation(location: FavoriteLocation) {
        val dbLocation = location.toEntity()
        viewModelScope.launch {
            database.insertFavoriteLocation(dbLocation)
        }
    }

    val showToast: SingleLiveEvent<String> = SingleLiveEvent()
}