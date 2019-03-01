package contact.pipe.contactsowner

import contact.architecture.Pipe
import contact.architecture.Pipeline
import javax.inject.Inject

class ContactsOwnerPipeline @Inject constructor() : Pipeline() {

    @Inject
    internal lateinit var observeContactsOwnerPipe: ObserveContactsOwnerPipe
    @Inject
    internal lateinit var routeToNavigatorFragmentPipe: RouteToNavigatorFragmentPipe

    override fun create(): List<Pipe> = listOf(observeContactsOwnerPipe,
            routeToNavigatorFragmentPipe)
}
