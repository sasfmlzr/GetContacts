package contact.pipe.location

import contact.architecture.EventModel
import contact.architecture.Pipe
import contact.architecture.ViewEvent
import contact.usecase.feature.ObserveOwnerContactByIdUseCase
import io.reactivex.Observable
import javax.inject.Inject

class RequestLocationsByIdEvent(val id: String) : ViewEvent

internal class ObserveLocationsByIdPipe @Inject constructor(
        private val observeOwnerContactByIdUseCase: ObserveOwnerContactByIdUseCase
) : Pipe {
    override fun apply(upstream: Observable<ViewEvent>) =
            upstream.ofType(RequestLocationsByIdEvent::class.java)
                    .map { it.id }
                    .flatMap { id ->
                        observeOwnerContactByIdUseCase.buildUseCaseObservable(id)
                                .map<EventModel> {
                                    LocationEventModel(it)
                                }.toObservable()
                    }
}
