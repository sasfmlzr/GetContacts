package contact.pipe.location

import contact.architecture.Pipe
import contact.architecture.Pipeline
import contact.pipe.contacts.ContactsInitPipe
import javax.inject.Inject

class LocationPipeline @Inject constructor() : Pipeline() {

    @Inject
    internal lateinit var contactsInitPipe: ContactsInitPipe

    override fun create(): List<Pipe> = listOf(contactsInitPipe)
}
