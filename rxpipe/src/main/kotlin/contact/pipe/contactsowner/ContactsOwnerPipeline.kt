package contact.pipe.contactsowner

import contact.architecture.Pipe
import contact.architecture.Pipeline
import javax.inject.Inject

class ContactsOwnerPipeline @Inject constructor() : Pipeline() {

    @Inject
    internal lateinit var contactsOwnerInitPipe: ContactsOwnerInitPipe
    @Inject
    internal lateinit var contactsOwnerPushPipe: ContactsOwnerPushPipe
    @Inject
    internal lateinit var observeContactsOwnerPipe: ObserveContactsOwnerPipe

    override fun create(): List<Pipe> = listOf(contactsOwnerInitPipe,
            contactsOwnerPushPipe,
            observeContactsOwnerPipe)
}
