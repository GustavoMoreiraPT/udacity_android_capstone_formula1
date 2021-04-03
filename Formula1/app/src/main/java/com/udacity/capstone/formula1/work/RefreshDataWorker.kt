package com.udacity.capstone.formula1.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.udacity.capstone.formula1.database.F1Database.Companion.getInstance
import com.udacity.capstone.formula1.dependencyinjection.Injection
import retrofit2.HttpException

class RefreshDataWorker(appContext: Context, params: WorkerParameters):
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        val database = getInstance(applicationContext)
        val di = Injection()
        val repository = di.repository
        return try {
            repository.updateConstructors()
            repository.updateDrivers()
            Result.success()
        } catch (e : HttpException) {
            Result.retry()
        }
    }
}