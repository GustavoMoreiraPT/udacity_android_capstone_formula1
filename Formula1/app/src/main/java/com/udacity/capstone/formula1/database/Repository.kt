package com.udacity.capstone.formula1.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.udacity.capstone.formula1.api.*
import com.udacity.capstone.formula1.dto.Constructor
import com.udacity.capstone.formula1.dto.Driver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class Repository(private val database: F1Database) {

    val allConstructors: LiveData<List<Constructor>> =
        Transformations.map(database.f1DatabaseDao.getAllConstructors()) { it.toDTO() }

    val allDrivers: LiveData<List<Driver>> =
        Transformations.map(database.f1DatabaseDao.getAllDrivers()) { it.toDTO() }

    suspend fun updateConstructors() {
        withContext(Dispatchers.IO) {
            val response = F1GatewayImpl.retrofitService.getConstructors()
            val parsedConstructors = parseConstructorsJsonResult(JSONObject(response))
            val dbConstructors = parsedConstructors.toEntity()

            database.f1DatabaseDao.clearConstructors()
            dbConstructors.forEach { database.f1DatabaseDao.insertConstructor(it) }
        }
    }

    suspend fun updateDrivers() {
        withContext(Dispatchers.IO) {
            val response = F1GatewayImpl.retrofitService.getDrivers()
            val parsedDrivers = parseDriversJsonResult(JSONObject(response))
            val dbDrivers = parsedDrivers.toEntity()

            database.f1DatabaseDao.clearDrivers()
            dbDrivers.forEach { database.f1DatabaseDao.insertDriver(it) }
        }
    }
}