package contact.pipe.location

import contact.architecture.Pipe
import contact.architecture.Pipeline
import contact.pipe.common.ObserveContactsOwnerPipe
import javax.inject.Inject

class LocationPipeline @Inject constructor() : Pipeline() {

    @Inject
    internal lateinit var observeContactsOwnerPipe: ObserveContactsOwnerPipe

    override fun create(): List<Pipe> = listOf(observeContactsOwnerPipe)
}
