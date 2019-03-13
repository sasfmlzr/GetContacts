package contact.pipe.location

import contact.architecture.*
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
                    .flatMap { event ->
                        observeLocationsByIdUseCase
                                .buildUseCaseObservable(
                                        ObserveLocationsByIdUseCase.Params(
                                                event.id,
                                                event.fromDate,
                                                event.toDate))
                                .toObservable()
                                .map<EventModel> { list ->
                                    LocationEventModel(list)
                                }.appendToolbarResults("From ${event
                                        .fromDate.toString("yyyy.MM.dd")} to ${event
                                        .toDate.toString("yyyy.MM.dd")}")
                                .appendErrorResults()
                    }
}
