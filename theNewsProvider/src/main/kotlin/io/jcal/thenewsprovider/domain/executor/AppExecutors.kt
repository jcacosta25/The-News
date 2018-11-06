package io.jcal.thenewsprovider.domain.executor

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject


const val THREAD_COUNT = 3

/**
 * Global executor pools for the whole application.
 *
 * Grouping tasks like this avoids the effects of task starvation (e.g. disk reads don't wait behind
 * webservice requests).
 */
open class AppExecutors @Inject constructor() {

    val diskIO: Executor
    val networkIO: Executor
    val mainThread: Executor

    init {
        diskIO = DiskIOThreadExecutor()
        networkIO = Executors.newFixedThreadPool(THREAD_COUNT)
        mainThread = MainThreadExecutor()
    }
}


class MainThreadExecutor @Inject constructor() : Executor {

//    private val mainThreadHandler = Handler(Looper.getMainLooper())

    override fun execute(command: Runnable) {
        Handler(Looper.getMainLooper()).post(command)
    }
}

class DiskIOThreadExecutor @Inject constructor() : Executor {

//    private val diskIO = Executors.newSingleThreadExecutor()

    override fun execute(command: Runnable) {
        Executors.newSingleThreadExecutor().execute(command)
    }
}