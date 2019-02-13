package contact.di.core

import contact.di.base.WorkerScope
import contact.worker.GetLocationWorker
import dagger.Subcomponent

@WorkerScope
@Subcomponent
interface WorkerComponent{
    fun inject(worker: GetLocationWorker)
}
