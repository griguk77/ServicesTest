package ru.studyguk.servicestest

import android.content.Context
import android.util.Log
import androidx.work.*

class MyWorker(
    context: Context,
    private val workerParameters: WorkerParameters
) : Worker(context, workerParameters) {

    override fun doWork(): Result {
        log("onHandleWork")
        val page = workerParameters.inputData.getInt(PAGE, 0)
        for (i in 0 until 5) {
            Thread.sleep(1000)
            log("Timer $i $page")
        }
        return Result.success()
    }

    private fun log(message: String) {
        Log.d("RRR", "MyJobIntentService $message")
    }

    companion object {
        private const val PAGE = "page"
        const val WORK_NAME = "work_name"

        fun makeRequest(page: Int) = OneTimeWorkRequestBuilder<MyWorker>()
            .setInputData(workDataOf(PAGE to page))
            .setConstraints(makeConstraints())
            .build()

        private fun makeConstraints() = Constraints.Builder()
            .setRequiresCharging(true)
            .build()
    }
}