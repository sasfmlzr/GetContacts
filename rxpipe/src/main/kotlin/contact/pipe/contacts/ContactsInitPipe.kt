package contact.pipe.contacts

import contact.architecture.Pipe
import contact.architecture.Result
import contact.architecture.ViewEvent
import contact.architecture.appendLoadingAndErrorResults
import contact.architecture.logging.Logger
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.ObservableSource
import javax.inject.Inject

class ContactsInitEvent : ViewEvent

internal class ContactsInitPipe @Inject constructor(
        private val logger: Logger
) : Pipe {

    override fun apply(upstream: Observable<ViewEvent>): ObservableSource<Result> =
            upstream.ofType(ContactsInitEvent::class.java).flatMap {
                logContact()
                        .toObservable<Result>()

                        .concatWith (
                                Observable.just(ContactsResult("Alexey")))

                        .appendLoadingAndErrorResults()
            }

    private fun logContact(): Completable = Completable.fromAction {
        logger.d("ContactsInitPipe", "EXECUTE")
    }
}
