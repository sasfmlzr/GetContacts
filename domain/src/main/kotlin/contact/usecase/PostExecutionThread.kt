package contact.usecase

import io.reactivex.Scheduler

interface PostExecutionThread {
    fun getScheduler(): Scheduler
}
