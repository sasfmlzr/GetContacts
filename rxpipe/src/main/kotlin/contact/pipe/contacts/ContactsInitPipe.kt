package contact.pipe.contacts

import contact.architecture.Pipe
import contact.architecture.EventModel
import contact.architecture.ViewEvent
import contact.architecture.logging.Logger
import contact.storage.ContactStorage
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.ObservableSource
import javax.inject.Inject

class ContactsInitEvent : ViewEvent

internal class ContactsInitPipe @Inject constructor(
        private val logger: Logger
) : Pipe {

    override fun apply(upstream: Observable<ViewEvent>): ObservableSource<EventModel> =
            upstream.ofType(ContactsInitEvent::class.java).flatMap {
                logContact()
                        .toObservable<EventModel>()
                        .concatWith(
                                Observable.just(ContactsInitEventModel(contactsResult = "Alexey")))
            }

    private fun logContact(): Completable = Completable.fromAction {
        logger.d("ContactsInitPipe", "EXECUTE")
    }
}
