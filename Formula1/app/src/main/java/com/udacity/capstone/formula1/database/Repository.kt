package com.udacity.capstone.formula1.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.udacity.capstone.formula1.dto.Constructor
import com.udacity.capstone.formula1.dto.Driver

class Repository(private val database: F1Database) {

    val allConstructors: LiveData<List<Constructor>> =
        Transformations.map(database.f1DatabaseDao.getAllConstructors()) { it.toDTO() }

    val allDrivers: LiveData<List<Driver>> =
        Transformations.map(database.f1DatabaseDao.getAllDrivers()) { it.toDTO() }
}