package contact.pipe.contactsowner

import contact.architecture.Pipe
import contact.architecture.EventModel
import contact.architecture.ViewEvent
import contact.architecture.logging.Logger
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.ObservableSource
import javax.inject.Inject

class ContactsOwnerPushEvent : ViewEvent

internal class ContactsOwnerPushPipe @Inject constructor(
        private val logger: Logger
) : Pipe {

    override fun apply(upstream: Observable<ViewEvent>): ObservableSource<EventModel> =
            upstream.ofType(ContactsOwnerPushEvent::class.java).flatMap {
                logContact()
                        .toObservable<EventModel>()
                        .concatWith (
                                Observable.just(ContactsOwnerPushEventModel(contactsPushResult = "Homa")))
            }

    private fun logContact(): Completable = Completable.fromAction {
        logger.d("ContactsOwnerPushPipe", "EXECUTE")
    }
}
