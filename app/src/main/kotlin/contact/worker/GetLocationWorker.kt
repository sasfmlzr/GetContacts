package contact.worker

import android.content.Context
import androidx.annotation.NonNull
import androidx.work.WorkerParameters
import contact.architecture.base.BaseDBWorker
import contact.di.core.WorkerComponent

class GetLocationWorker(@NonNull context: Context,
                        @NonNull workerParams: WorkerParameters
) : BaseDBWorker(context, workerParams) {

    override fun inject(component: WorkerComponent) = component.inject(this)

    override fun execute() {

    }

}
