package contact.pipe.location

import contact.api.location.GetLocation
import contact.architecture.EventModel
import contact.architecture.Pipe
import contact.architecture.ViewEvent
import contact.architecture.appendErrorResults
import contact.usecase.feature.GetMinMaxDateUseCase
import io.reactivex.Observable
import javax.inject.Inject

class RequestMinMaxDateEvent(val locations: List<GetLocation>) : ViewEvent

internal class GetMinMaxDatePipe @Inject constructor(
        private val getMinMaxDateUseCase: GetMinMaxDateUseCase
) : Pipe {
    override fun apply(upstream: Observable<ViewEvent>): Observable<EventModel> =
            upstream.ofType(RequestMinMaxDateEvent::class.java)
                    .flatMap {
                        getMinMaxDateUseCase
                                .buildUseCaseObservable(it.locations)
                                .map<EventModel> { dates ->
                                    MinMaxDateEventModel(dates.first, dates.second)
                                }.toObservable()
                                .appendErrorResults()
                    }
}
