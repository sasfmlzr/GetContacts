package contact.pipe.contactsowner

import contact.architecture.EventModel
import contact.architecture.Pipe
import contact.architecture.ViewEvent
import contact.usecase.feature.ObserveAllContactsUseCase
import io.reactivex.Observable
import javax.inject.Inject

class RequestObserveContactsOwnerEvent : ViewEvent

internal class ObserveContactsOwnerPipe @Inject constructor(
        private val observeAllContacts: ObserveAllContactsUseCase
) : Pipe {
    override fun apply(upstream: Observable<ViewEvent>): Observable<EventModel> =
            upstream.ofType(RequestObserveContactsOwnerEvent::class.java)
                    .flatMap {
                        observeAllContacts.buildUseCaseObservable(Unit)
                                .map<EventModel> {
                                    ObserveContactsOwnerEventModel(it)
                                }
                    }
}
