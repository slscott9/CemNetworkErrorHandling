package com.example.networkerrorhandling.worker

import android.content.Context
import androidx.hilt.Assisted
import androidx.hilt.work.WorkerInject
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.networkerrorhandling.data.CemeteryRepository
import timber.log.Timber
import java.lang.Exception

class RefreshDataWorker @WorkerInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
    private val cemeteryRepository: CemeteryRepository
) : CoroutineWorker(context, workerParameters){

    companion object {
        const val WORK_NAME = "refresh_data_worker"
    }


    override suspend fun doWork(): Result {
        try {
            cemeteryRepository.refreshCemeteryList()
            Timber.i("Work request for refreshing database is run")
        }catch (e: Exception){
            return Result.retry()
        }
        return Result.success()
    }
}