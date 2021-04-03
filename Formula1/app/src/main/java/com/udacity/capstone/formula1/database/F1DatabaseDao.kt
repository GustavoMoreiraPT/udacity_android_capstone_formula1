package com.udacity.capstone.formula1.database

import androidx.lifecycle.LiveData
import androidx.room.*
import retrofit2.http.GET

@Dao
interface F1DatabaseDao {

    // Constructor
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertConstructor(constructor: Constructor)

    @Update
    fun updateConstructor(constructor: Constructor)

    @Query("SELECT * FROM constructor_info_table WHERE id = :key")
    fun getConstructor(key: Long): Constructor?

    @Query("DELETE FROM constructor_info_table")
    fun clearConstructors()

    @Query("SELECT * FROM constructor_info_table ORDER BY id DESC")
    fun getAllConstructors(): LiveData<List<Constructor>>

    // Driver
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDriver(driver: Driver)

    @Update
    fun updateDriver(driver: Driver)

    @Query("SELECT * FROM driver_info_table WHERE id = :key")
    fun getDriver(key: Long): Driver?

    @Query("DELETE FROM driver_info_table")
    fun clearDrivers()

    @Query("SELECT * FROM driver_info_table ORDER BY id DESC")
    fun getAllDrivers(): LiveData<List<Driver>>

    // Favorite Locations
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteLocation(favoriteLocation: FavoriteLocation)

    @Query("SELECT * FROM locations_info_table ORDER BY id DESC")
    fun getAllFavoriteLocations() : LiveData<List<FavoriteLocation>>
}