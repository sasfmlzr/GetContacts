package contact.pipe.location

import contact.architecture.Pipe
import contact.architecture.Pipeline
import contact.pipe.contactsowner.ObserveContactsOwnerPipe
import javax.inject.Inject

class LocationPipeline @Inject constructor() : Pipeline() {

    @Inject
    internal lateinit var observeLocationsByIdPipe: ObserveLocationsByIdPipe

    override fun create(): List<Pipe> = listOf(observeLocationsByIdPipe)
}
