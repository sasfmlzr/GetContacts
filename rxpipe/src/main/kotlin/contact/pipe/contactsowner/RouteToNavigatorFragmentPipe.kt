package contact.pipe.contactsowner

import contact.architecture.EventModel
import contact.architecture.Pipe
import contact.architecture.Router
import contact.architecture.ViewEvent
import io.reactivex.Observable
import io.reactivex.ObservableSource
import javax.inject.Inject

class RouteToNavigatorFragmentEvent(val id: String) : ViewEvent

internal class RouteToNavigatorFragmentPipe @Inject constructor(
        private val router: Router
) : Pipe {
    override fun apply(upstream: Observable<ViewEvent>): ObservableSource<EventModel> =
            upstream.ofType(RouteToNavigatorFragmentEvent::class.java)
                    .map { it.id }
                    .doOnNext { router.navigateContactOwnerToLocationFragment(it) }
                    .ignoreElements()
                    .toObservable()
}
