package contact.pipe.location

import contact.architecture.Pipe
import contact.architecture.Pipeline
import contact.pipe.contactsowner.ContactsOwnerInitPipe
import javax.inject.Inject

class LocationPipeline @Inject constructor() : Pipeline() {

    @Inject
    internal lateinit var contactsInitPipe: ContactsOwnerInitPipe

    override fun create(): List<Pipe> = listOf(contactsInitPipe)
}
