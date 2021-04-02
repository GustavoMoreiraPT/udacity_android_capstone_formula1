package com.udacity.capstone.formula1.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Driver::class, Constructor::class], version = 1, exportSchema = false)
abstract class F1Database : RoomDatabase() {

    abstract val f1DatabaseDao: F1DatabaseDao

    companion object {

        @Volatile
        private var INSTANCE : F1Database? = null

        fun getInstance(context: Context): F1Database {
            synchronized(this){
                var instance = INSTANCE
                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        F1Database::class.java,
                        "formula1_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}