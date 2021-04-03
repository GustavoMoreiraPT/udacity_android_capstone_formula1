package com.udacity.capstone.formula1.dependencyinjection

import android.app.Application
import android.os.Build
import androidx.work.*
import com.udacity.capstone.formula1.database.F1Database
import com.udacity.capstone.formula1.database.Repository
import com.udacity.capstone.formula1.work.RefreshDataWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class Injection : Application() {

    val database by lazy { F1Database.getInstance(this) }

    val repository by lazy { Repository(database) }

    val scope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()
        delayedInit()
    }

    private fun delayedInit() {
        scope.launch {
            setupRecurringWork()
        }
    }

    private fun setupRecurringWork() {
        val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.UNMETERED)
                .setRequiresBatteryNotLow(true)
                .setRequiresCharging(true)
                .apply {
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        setRequiresDeviceIdle(true)
                    }
                }.build()

        val repeatingRequest =
                PeriodicWorkRequestBuilder<RefreshDataWorker>(1, TimeUnit.DAYS)
                        .setConstraints(constraints)
                        .build()

        WorkManager.getInstance().enqueueUniquePeriodicWork(
                RefreshDataWorker.WORK_NAME,
                ExistingPeriodicWorkPolicy.KEEP,
                repeatingRequest
        )
    }
}