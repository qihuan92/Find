package com.qihuan.find.work

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 * MainWorker
 * @author qi
 * @date 2018/10/19
 */
class MainWorker(context: Context, parameters: WorkerParameters) : Worker(context, parameters) {

    override fun doWork(): Result {
        Log.d("WorkManager", "WorkManager doWork")
        return Result.SUCCESS
    }
}