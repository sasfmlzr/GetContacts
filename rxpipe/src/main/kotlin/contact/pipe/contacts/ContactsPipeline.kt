package contact.pipe.contacts

import contact.architecture.Pipe
import contact.architecture.Pipeline
import javax.inject.Inject

class ContactsPipeline @Inject constructor() : Pipeline() {

    @Inject
    internal lateinit var contactsInitPipe: ContactsInitPipe

    override fun create(): List<Pipe> = listOf(contactsInitPipe)
}
