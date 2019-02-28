package contact.pipe.location

import contact.architecture.EventModel
import contact.architecture.Pipe
import contact.architecture.ViewEvent
import contact.usecase.feature.ObserveLocationsByIdUseCase
import io.reactivex.Observable
import org.joda.time.LocalDate
import javax.inject.Inject

class RequestLocationsByIdAndDateEvent(val id: String,
                                       val fromDate: LocalDate,
                                       val toDate: LocalDate) : ViewEvent

internal class ObserveLocationsByIdPipe @Inject constructor(
        private val observeLocationsByIdUseCase: ObserveLocationsByIdUseCase
) : Pipe {
    override fun apply(upstream: Observable<ViewEvent>): Observable<EventModel> =
            upstream.ofType(RequestLocationsByIdAndDateEvent::class.java)
                    .flatMap {
                        observeLocationsByIdUseCase
                                .buildUseCaseObservable(
                                        ObserveLocationsByIdUseCase.Params(
                                                it.id,
                                                it.fromDate,
                                                it.toDate))
                                .map<EventModel> {list ->
                                    LocationEventModel(list)
                                }.toObservable()
                    }
}
