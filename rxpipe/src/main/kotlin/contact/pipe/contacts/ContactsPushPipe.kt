package contact.pipe.contacts

import contact.architecture.Pipe
import contact.architecture.EventModel
import contact.architecture.ViewEvent
import contact.architecture.logging.Logger
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.ObservableSource
import javax.inject.Inject

class ContactsPushEvent : ViewEvent

internal class ContactsPushPipe @Inject constructor(
        private val logger: Logger
) : Pipe {

    override fun apply(upstream: Observable<ViewEvent>): ObservableSource<EventModel> =
            upstream.ofType(ContactsPushEvent::class.java).flatMap {
                logContact()
                        .toObservable<EventModel>()
                        .concatWith (
                                Observable.just(ContactsPushEventModel(contactsPushResult = "Homa")))
            }

    private fun logContact(): Completable = Completable.fromAction {
        logger.d("ContactsPushPipe", "EXECUTE")
    }
}
