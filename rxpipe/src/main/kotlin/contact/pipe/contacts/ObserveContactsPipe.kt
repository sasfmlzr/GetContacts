package contact.pipe.contacts

import contact.architecture.Pipe
import contact.architecture.EventModel
import contact.architecture.ViewEvent
import contact.usecase.feature.ObserveAllContactsUseCase
import io.reactivex.Observable
import javax.inject.Inject

class RequestObserveContactsEvent : ViewEvent

internal class ObserveContactsPipe @Inject constructor(
        private val observeAllContacts: ObserveAllContactsUseCase
) : Pipe {
    override fun apply(upstream: Observable<ViewEvent>) =
            upstream.ofType(RequestObserveContactsEvent::class.java)
                    .flatMap {
                        observeAllContacts.buildUseCaseObservable(Unit)
                                .map<EventModel> {
                                    ObserveContactsEventModel(it)
                                }
                    }
}