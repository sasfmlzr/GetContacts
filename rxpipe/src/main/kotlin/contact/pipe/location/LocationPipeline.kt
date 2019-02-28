package contact.pipe.location

import contact.architecture.Pipe
import contact.architecture.Pipeline
import javax.inject.Inject

class LocationPipeline @Inject constructor() : Pipeline() {

    @Inject
    internal lateinit var observeLocationsByIdPipe: ObserveLocationsByIdPipe
    @Inject
    internal lateinit var getMinMaxDatePipe: GetMinMaxDatePipe

    override fun create(): List<Pipe> = listOf(observeLocationsByIdPipe, getMinMaxDatePipe)
}
